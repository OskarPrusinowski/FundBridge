<template>
    <div>
        <v-btn @click="isExpanded = !isExpanded" color="white"
            class="opacity-55 text-left flex items-center justify-start w-full">
            <span class="mr-2 normal-case">{{ title }}</span>
            <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24"
                xmlns="http://www.w3.org/2000/svg">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 9l6 6 6-6" />
            </svg>
        </v-btn>

        <v-expand-transition>
            <div v-if="isExpanded" class="pl-4">
                <!-- Removed default background color and elevation -->
                <v-list class="transparent-background text-white" elevation="0">
                    <v-list-item>
                        <v-list-item-content>
                            {{ value }}
                        </v-list-item-content>
                    </v-list-item>
                </v-list>
            </div>
        </v-expand-transition>
    </div>
</template>

<script>
export default {
    props: {
        title: {
            type: String,
            required: true,
        },
        value: {
            type: String,
            required: true,
        }
    },
    data() {
        return {
            isExpanded: false, // Controls the expanded state
        };
    },
};
</script>

<style scoped>
.transparent-background {
    background-color: transparent !important;
    /* Disable background color */
    box-shadow: none !important;
    /* Remove shadow effect if any */
}
</style>
