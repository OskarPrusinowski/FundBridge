<template>
    <div>
        <v-sheet class="bg-LightBlue" :width="width" :height="height"></v-sheet>
        <v-divider :thickness="5"></v-divider>
    </div>
</template>
<script>
export default {
    data() {
        return {
            defaultWidth: 189,
            defaultHeight: 12,
            defaultMobileWidth: 90
        }
    },
    computed: {
        multiplier() {
            return this.$store.getters.getMultiplier
        },
        isMobile() {
            return this.$store.getters.getIsMobile;
        },
        height() {
            return this.defaultHeight * this.multiplier;
        },
        width() {
            return this.isMobile ? this.defaultMobileWidth : this.defaultWidth * this.multiplier;
        }
    },
}
</script>