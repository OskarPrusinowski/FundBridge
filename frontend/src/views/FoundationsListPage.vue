<template>
    <v-sheet :width="isMobile ? '90vw' : '85vw'" :class="[{ 'cmx-16': !isXS }, 'mt-16', ' mx-auto']">

        <div>
            <div class="title c-h1 text-DarkBlue fw-bold mt-16 pt-16" :class="[isXS ? 'c-h3' : 'c-h3']">
                Lista fundacji
            </div>
            <SliderProgress class="cmt-6 mb-2" />
        </div>
        <FoundationList :slider="false" />
    </v-sheet>
</template>

<script>
import FoundationList from './Foundations/List.vue'

export default {
    components: {
        FoundationList,
    },
    computed: {
        isXS() {
            return this.$store.getters.getIsXS;
        },
        isMobile() {
            return this.$store.getters.getIsMobile
        },
    },
    methods: {
        listFoundations() {
            this.$store.dispatch("listFoundations");
        },
        getId() {
            var foundationId = this.$route.params.foundationId;
            return foundationId;
        },
        init() {
            this.listFoundations();
        },
    },
    created() {
        this.init();
    }
}
</script>
