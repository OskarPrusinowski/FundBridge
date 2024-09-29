<template>
    <div class="d-flex">
        <v-hover>
            <template v-slot:default="{ isHovering, props }">
                <v-sheet style="z-index: 10">
                    <v-sheet :width="sheetWidth" :height="isMobile ? sheetHeight * 1.15 : sheetHeight" v-bind="props"
                        class="bg-white goTo clickable foundation" :class="[isMobile ? 'mx-1' : 'mx-2']"
                        @click="goTo(foundation.id)">

                        <Logo :foundation="foundation" :isHovering="isHovering" />

                        <v-sheet style="z-index: 10" :width="sheetWidth"
                            :height="isMobile ? titleHeight * 1.5 : titleHeight"
                            :class="['d-flex foundation-content transition-able flex-column']">

                            <div :class="['cmt-2 cmr-2 cml-6 mb-auto full-height']">
                                <div :class="[isHovering ? 'text-white' : 'text-DarkBlue', 'date c-text-41']">
                                    {{ foundation.date ? $moment(foundation.date).format('DD.MM.YYYY') : '' }}
                                </div>
                                <div
                                    :class="[isHovering ? 'text-LightBlue' : 'text-DarkBlue', 'title fw-600 c-text-2 lh-12']">
                                    {{ foundation.name }}
                                </div>
                            </div>
                        </v-sheet>
                    </v-sheet>

                    <div v-if="authenticated && !isMobile" class="text-center">
                        <DestroyDialog @destroy="destroy(foundation.id)" :label="'Foundation'" size="default" />
                        <!-- Updated label -->
                    </div>
                </v-sheet>
            </template>
        </v-hover>
    </div>
</template>

<script>
import Logo from './Logo.vue';

export default {
    props: ["foundation"], // Updated to accept foundation prop
    components: { Logo },
    data() {
        return {
            defaultWidth: 258,
            defaultHeight: 350,
            defaultTitleHeight: 140
        };
    },
    computed: {
        multiplier() {
            return this.$store.getters.getMultiplier;
        },
        additionalMultiplier() {
            if (document.body.clientWidth > 1800) {
                return 1.2;
            }
            if (document.body.clientWidth > 1500) {
                return 1.1;
            }
            return 0.95;
        },
        titleHeight() {
            return this.multiplier * this.defaultTitleHeight * this.additionalMultiplier;
        },
        sheetHeight() {
            return this.multiplier * this.defaultHeight * this.additionalMultiplier;
        },
        sheetWidth() {
            return this.multiplier * this.defaultWidth * this.additionalMultiplier;
        },
        isMobile() {
            return this.$store.getters.getIsMobile;
        },
        authenticated() {
            return this.$store.getters.getAuthenticated;
        }
    },
    methods: {
        goTo(id) {
            this.$router.push({ name: "Foundation", params: { id } }); // Updated to navigate to foundation
        },
        async destroy(id) {
            await this.$store.dispatch("deleteFoundation", id); // Updated action to delete foundation
            this.changed();
        },
        changed() {
            this.$store.dispatch("listFoundations"); // Updated action to list foundations
        }
    },
}
</script>

<style scoped>
.title {
    white-space: normal;
    overflow-wrap: break-word;
    word-break: break-word;
}

.foundation-content {
    box-sizing: border-box;
    background: linear-gradient(rgb(var(--v-theme-DarkBlue)) 50%, rgb(var(--v-theme-LightGray)) 50%);
    background-position: 100% 100%;
    background-size: 100% 200%;
    transition: 0.3s;
    position: relative;
}

.foundation:hover>.foundation-content {
    background-position: 100% 0;
}

.left-box,
.right-box {
    display: inline-block;
    vertical-align: top;
    position: absolute;
    bottom: 15px;
    margin-left: -20px;
    background: linear-gradient(rgb(var(--v-theme-LightGray)) 50%, rgb(var(--v-theme-LightGray)) 50%);
}
</style>
