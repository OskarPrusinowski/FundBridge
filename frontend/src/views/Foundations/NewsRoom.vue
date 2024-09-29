<template>
    <v-sheet :width="'auto'">
        <div>
            <div class="title text-DarkBlue c-sb mb-4 clickable" :class="[isMobile ? 'c-h3' : 'c-h5']"
                @click="goToList">
                {{ label }}
            </div>
            <SliderProgress />
        </div>
        <List />
        <div class="d-flex">
            <v-hover>
                <template v-slot:default="{ isHovering, props }">
                    <RedirectBtn v-bind="props" :isHovering="isHovering" class="mx-auto" @click="goToList"
                        :label="'Wszystkie fundacje'" />
                </template>
            </v-hover>
            <v-hover>
                <template v-slot:default="{ isHovering, props }">
                    <RedirectBtn v-bind="props" :isHovering="isHovering"
                        v-if="authenticated && !isMobile && !hideManage" class="mx-auto" @click="goToCreate"
                        :label="'Dodaj fundację'" />
                </template>
            </v-hover>
        </div>
    </v-sheet>
</template>

<script>
import List from './List.vue'; // Update to the correct path for your foundations list

export default {
    props: {
        label: {
            default: "Fundacje" // Updated label for foundations
        },
        foundationId: { // Changed from projectId to foundationId
            default: null
        },
        hideManage: {
            default: false
        }
    },
    components: {
        List // Assuming this component now handles foundations
    },
    data() {
        return {
            defaultHeight: 532,
        };
    },
    computed: {
        foundations() {
            return this.$store.getters.getFoundations; // Adjusted for foundations
        },
        authenticated() {
            return this.$store.getters.getAuthenticated;
        },
        isMobile() {
            return this.$store.getters.getIsMobile;
        },
        multiplier() {
            return this.$store.getters.getMultiplier;
        },
        width() {
            return this.isMobile ? '90vw' : 'calc(21.5vw + ' + (16 / 9) * this.sheetHeight + 'px)';
        },
        sheetHeight() {
            if (this.isMD) {
                return 0.7 * this.defaultHeight;
            }
            return this.multiplier * this.defaultHeight;
        },
    },
    methods: {
        goTo(id) {
            this.$router.push({ name: "ShowFoundation", params: { id: id } }); // Updated for foundations
        },
        goToCreate() {
            if (this.foundationId) { // Changed from projectId to foundationId
                this.$router.push({ name: "ShowFoundation", query: { foundationId: this.foundationId } }); // Updated for foundations
            } else {
                this.$router.push({ name: "ShowFoundation" });
            }
        },
        goToList() {
            this.$router.push({ name: "FoundationsList" });
        },
        listFoundations() {
            this.$store.dispatch("listFoundations")
        }
    },
    created() {
        this.listFoundations()
    }
}
</script>
