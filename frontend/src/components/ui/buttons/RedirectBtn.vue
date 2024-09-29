<template>
    <v-sheet rounded="pill"
        :class="['clickable d-flex align-center goTo uppercase redirect-btn', isHovering ? 'bg-NavyBlue' : 'bg-LightBlue']"
        flat @click="goTo" :width="width" :height="height">
        <div class="text-white text-center mx-auto fw-600" :class="[isMobile ? 'c-text-1' : 'c-text-6']">
            {{ label }}
        </div>
    </v-sheet>
</template>
<script>
export default {
    props: {
        label: {},
        destination: {},
        isHovering: {
            default: false
        },
    },
    data() {
        return {
            defaultWidth: 185,
            defaultMobileWidth: 357,
            defaultHeight: 52
        }
    },
    computed: {
        isMobile() {
            return this.$store.getters.getIsMobile
        },
        multiplier() {
            return this.$store.getters.getMultiplier
        },
        height() {
            return this.isMobile ? this.defaultHeight : this.defaultHeight * this.multiplier;
        },
        width() {
            return this.defaultWidth * this.multiplier;
        }
    },
    methods: {
        goTo() {
            if (this.destination) {
                this.$router.push({ name: this.destination })
            }
        }
    }
}
</script>
<style>
.redirect-btn {
    transition: 0.3s ease-out;
}
</style>