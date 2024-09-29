<template>
    <div class="text-center" v-if="authenticated">
        <v-snackbar v-model="showMessage" timeout="2000" color="DarkBlue" location="top">
            {{ message }}
        </v-snackbar>
    </div>
</template>
<script>
export default {
    computed: {
        showMessage: {
            get() {
                return this.$store.getters.getShowMessage;
            },
            set(val) {
                this.$store.commit("setShowMessage", val)
            }
        },
        message() {
            return this.$store.getters.getMessage;
        },
        authenticated() {
            return this.$store.getters.getAuthenticated
        }
    },
    methods: {
        open(message) {
            this.showMessage = true;
            this.message = message;
        }
    }
}
</script>