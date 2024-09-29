<template>
    <div :class="{ 'text-center': center }">
        <v-dialog v-model="dialog" width="auto">
            <template v-slot:activator="{ props }">
                <v-btn icon="mdi-trash-can" :color="color" :size="size" v-bind="props"></v-btn>
            </template>
            <v-card>
                <v-card-text>
                    Czy na pewno chcesz usunąć {{ label }}?
                </v-card-text>
                <v-card-actions>
                    <v-btn color="error" @click="dialog = false">Cofnij</v-btn>
                    <v-spacer></v-spacer>
                    <v-btn color="DarkBlue" @click="destroy">Usuń</v-btn>
                </v-card-actions>
            </v-card>
        </v-dialog>
    </div>
</template>
<script>
export default {
    data() {
        return {
            dialog: false,
        }
    },
    props: {
        color: {
            default: "white"
        },
        label: {
            default: ""
        },
        size: {
            default: "x-small"
        },
        center: {
            default: true
        }
    },
    methods: {
        destroy() {
            this.$emit("destroy")
            this.dialog = false
        }
    }
}
</script>