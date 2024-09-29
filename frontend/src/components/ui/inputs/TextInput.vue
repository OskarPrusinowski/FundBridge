<template>
    <v-sheet>
        <v-sheet :style="'position: absolute;margin-top:1px;'" class="cml-1 align-center d-flex " :height="iconHeight">
            <v-icon v-if="icon" color="DarkBlue">
                {{ icon }}
            </v-icon>
        </v-sheet>
        <input type="text" :style="'width:' + width + ';'" :height="height" :class="['input', icon ? 'cpl-10' : '']"
            :value="modelValue" @input="updateValue" :placeholder="placeholder">
    </v-sheet>
</template>
<script>
export default {
    props: {
        modelValue: {
            type: String,
            default: '',
        },
        icon: {
            default: null
        },
        placeholder: {
            default: ""
        }
    },
    emits: ['update:modelValue'],
    data() {
        return {
            defaultWidth: 370,
            defaultHeight: 64,
        }
    },
    computed: {
        multiplier() {
            return this.$store.getters.getMultiplier
        },
        isXS() {
            return this.$store.getters.getIsXS;
        },
        isBigger() {
            return this.$store.getters.getIsXL || this.$store.getters.getIsXXL
        },
        isMobile() {
            return this.$store.getters.getIsMobile;
        },
        width() {
            return this.isXS ? "85vw" : this.multiplier * this.defaultWidth + "px"
        },
        height() {
            return this.multiplier * this.defaultHeight
        },
        iconHeight() {
            return this.isXS ? this.height + 5 : (this.isMobile ? this.height : (this.isBigger ? this.height - 16 : this.height - 10));
        },
        marginTop() {
            if (this.isXS) {
                return 14;
            }
            return this.height / 2 - (20 * this.multiplier);
        },
    },
    methods: {
        updateValue(event) {
            this.$emit("update:modelValue", event.target.value)
        }
    }
}
</script>
<style>
.input {
    padding: 12px 20px;
    display: inline-block;
    border: 1px solid #184773;
    box-sizing: border-box;
    color: #184773;
}

.input:focus {
    outline: none;
}
</style>