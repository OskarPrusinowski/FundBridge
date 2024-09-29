<template>
    <v-sheet class="bg-DarkBlue d-flex align-center" :width="width" :height="height">
        <v-icon color="white" size="x-large" class="mx-auto">
            mdi-magnify
        </v-icon>
    </v-sheet>
</template>
<script>
export default {
    data() {
        return {
            defaulHeight: 64,
            defaultWidth: 64,
            defaultMobileWidth: 'auto',
        }
    },
    computed: {
        multiplier() {
            return this.$store.getters.getMultiplier
        },
        isMobile() {
            return this.$store.getters.getIsXS;
        },
        width() {
            return this.isMobile ? this.defaultMobileWidth : this.defaultWidth * this.multiplier;
        },
        height() {
            return this.defaulHeight * this.multiplier;
        },
    }
}
</script>