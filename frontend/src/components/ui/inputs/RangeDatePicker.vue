<template>
    <v-sheet>
        <Calendar :startDate="startDate" @update:startDate="updateStartDate" :endDate="endDate"
            @update:endDate="updateEndDate" />
    </v-sheet>
</template>
<script>
import Calendar from './Calendar.vue';

export default {
    components: {
        Calendar
    },
    props: {
        startDate: {
            default: '',
        },
        endDate: {
            default: '',
        },
    },
    emits: ['update:startDate', 'update:endDate'],
    data() {
        return {
            defaultWidth: 370,
            defaultHeight: 64
        }
    },
    computed: {
        label() {
            var label = "";
            if (this.startDate) {
                label += "Od " + this.$moment(this.startDate).format('DD-MM-YY');
            }
            if (this.endDate) {
                label += " Do " + this.$moment(this.endDate).format('DD-MM-YY');
            }
            return label
        },
        multiplier() {
            return this.$store.getters.getMultiplier
        },
        isXS() {
            return this.$store.getters.getIsXS;
        },
        width() {
            return this.isXS ? "85vw" : this.multiplier * this.defaultWidth + "px"
        },
        height() {
            return this.multiplier * this.defaultHeight
        }
    },
    methods: {
        updateStartDate(value) {
            this.$emit("update:startDate", value)
        },
        updateEndDate(value) {
            this.$emit("update:endDate", value)
        },
    }
}
</script>