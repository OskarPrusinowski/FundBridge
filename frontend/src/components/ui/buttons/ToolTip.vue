<template>
    <v-sheet>
        <v-tooltip :text="tip" location="bottom" :disabled="!showTips" open-delay="500">
            <template v-slot:activator="{ props }">
                <v-btn v-bind="props" :icon="icon" :disabled="disabled"></v-btn>
            </template>
        </v-tooltip>
    </v-sheet>
</template>
<script>
export default {
    props: {
        disabled: {
            default: false
        },
        icon: {
            default: ""
        },
        tip: {
            default: ""
        },
    },
    computed: {
        showTips() {
            return this.$store.getters.getShowTips
        }
    }
}
</script>