<template>
    <v-hover>
        <template v-slot:default="{ isHovering, props }">
            <v-sheet v-bind="props" :width="width" :height="height" v-auto-animate
                :class="['arrow-box transition-able clickable  d-flex text-center align-center', isHovering ? 'bg-DarkBlue' : 'bg-LightBlue']">
                <v-icon class="mx-auto" color="white" size="small">
                    {{ reversed ? 'mdi-arrow-left-thick' : 'mdi-arrow-right-thick' }}
                </v-icon>
            </v-sheet>
        </template>
    </v-hover>
</template>
<script>
export default {
    props: {
        reversed: {
            default: false
        },
        color: {
            default: false
        },
    },
    data() {
        return {
            defaultWidth: 52,
            defaultHeight: 52
        }
    },
    computed: {
        isMobile() {
            return this.$store.getters.getIsMobile
        },
        multiplier() {
            return this.isMobile ? 1 : this.$store.getters.getMultiplier
        },
        width() {
            return this.defaultWidth * this.multiplier;
        },
        height() {
            return this.defaultHeight * this.multiplier;
        },
    },
}

</script>
<style>
.arrow-box {
    transition: 0.3s ease-out;
}
</style>