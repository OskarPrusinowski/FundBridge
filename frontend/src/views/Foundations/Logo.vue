<template>
    <div class="logo cmt-8 cmb-2" :class="['logo', isHovering ? 'on-hover' : '']">
        <v-img v-if="foundation.logo" :src="src" :width="imgWidth" :height="imgHeight" class="mx-auto">
            <template v-slot:placeholder>
                <div class="d-flex justify-center align-center fill-height">
                    <v-progress-circular color="grey-lighten-4" indeterminate></v-progress-circular>
                </div>
            </template>
        </v-img>
        <v-sheet v-else :width="imgWidth" :height="imgHeight" class="mx-auto">
            <v-img :src="src" :width="emptyImgWidth" :height="emptyImgHeight" class="mx-auto">
                <template v-slot:placeholder>
                    <div class="d-flex align-center justify-center fill-height">
                        <v-progress-circular color="grey-lighten-4" indeterminate></v-progress-circular>
                    </div>
                </template>
            </v-img>
        </v-sheet>
    </div>
</template>

<script>
export default {
    props: ["foundation", "isHovering"], // Updated to accept foundation prop
    data() {
        return {
            defaultImgWidth: 258,
            defaultImgHeight: 192,
            defaultEmptyImgWidth: 145,
            defaultEmptyImgHeight: 158
        };
    },
    computed: {
        src() {
            if (this.foundation.logoId) {
                return "http://localhost:8080/files?id=" + this.foundation.logoId; // Ensure to handle logo path correctly
            }
            return "/logo_domyslne.svg"
        },
        multiplier() {
            return this.$store.getters.getMultiplier;
        },
        imgWidth() {
            return this.multiplier * this.defaultImgWidth;
        },
        imgHeight() {
            return this.multiplier * this.defaultImgHeight;
        },
        emptyImgWidth() {
            return this.multiplier * this.defaultEmptyImgWidth;
        },
        emptyImgHeight() {
            return this.multiplier * this.defaultEmptyImgHeight;
        },
    },
}
</script>

<style scoped>
.logo {
    transition: opacity .3s ease-in-out;
}

.logo:not(.on-hover) {
    opacity: 0.9;
}
</style>
