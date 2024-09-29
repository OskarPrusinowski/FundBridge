<template>
    <div>
        <div class="d-flex flex-wrap justify-center">

            <v-slide-group ref="Slider" v-if="slider">
                <template v-slot:prev="{}">
                    <!-- Previous Button (Optional) -->
                </template>
                <template v-slot:next="{}">
                </template>

                <div class="d-flex flex-wrap">
                    <div class="d-flex flex-column">
                        <div class="d-flex">
                            <Single v-for="(foundation, i) in foundations.slice(0, Math.ceil(foundations.length / 2))"
                                :key="i" :foundation="foundation" />
                        </div>
                        <div class="d-flex">
                            <Single v-for="(foundation, i) in foundations.slice(Math.ceil(foundations.length / 2))"
                                :key="i + Math.ceil(foundations.length / 2)" :foundation="foundation" />
                        </div>
                    </div>
                </div>
            </v-slide-group>

            <div class="d-flex flex-wrap justify-space-evenly" v-else>
                <Single v-for="(foundation, index) in foundations" :key="foundation.id" :foundation="foundation" />
            </div>
        </div>

        <div class="d-flex cmy-4" v-if="slider">
            <div class="me-auto"></div>
            <div :class="['d-flex cmb-8']">
                <div @click="scroll('prev')">
                    <Arrow :reversed="true" />
                </div>
                <div @click="scroll('next')">
                    <Arrow />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Single from './Single.vue';

export default {
    components: {
        Single
    },
    props: {
        slider: {
            default: true
        }
    },
    computed: {
        foundations() {
            return this.$store.getters.getFoundations;
        }
    },
    methods: {
        scroll(location) {
            console.log("LOCATION");
            console.log(location);
            this.$refs.Slider.scrollTo(location);
        },
    }
}
</script>

<style scoped>
.v-slide-group__prev .v-slide-group__next {
    display: none !important;
}

.flex-wrap {
    display: flex;
    flex-wrap: wrap;
}

.flex-item {
    flex-basis: 100%;
    /* Adjust as needed for your design */
}
</style>
