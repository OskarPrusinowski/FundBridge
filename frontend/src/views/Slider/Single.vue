<template>
    <div class=" mx-auto d-flex flex-column align-center">
        <v-sheet class="d-flex flex-column">
            <v-sheet class="d-flex single-slider" :height="sheetHeight">
                <v-sheet :width="'21.5vw'" :height="sheetHeight" class="bg-NavyBlue d-flex align-end flex-column">
                    <v-sheet class="d-flex align-end flex-column cmt-16 bg-white" :height="contentHeight"
                        :width="'21.5vw'">
                        <v-sheet class="text-NavyBlue fw-700 c-h5 cmr-7 cmt-10" v-if="project" :width="'17.5vw'"
                            :height="'80'">
                            {{ project.name }}
                        </v-sheet>
                        <v-hover>
                            <template v-slot:default="{ isHovering, props }">
                                <RedirectBtn class="cmr-4 cmt-10 goTo" v-bind="props" :isHovering="isHovering"
                                    :label="'Zobacz więcej'" @click="goTo()" v-if="project" />
                            </template>
                        </v-hover>
                    </v-sheet>
                    <div class="actions d-flex cma-5" v-if="authenticated && project.id">
                        <DestroyDialog @destroy="destroy(project.id)" :label="'Project'" size="default"
                            color="LightBlue" />
                        <v-btn color="LightBlue" icon="mdi-pencil" class="mx-1"></v-btn>
                        <v-btn color="LightBlue" v-if="!isFirst" icon="mdi-arrow-left" class="mx-1"
                            @click="move(-1)"></v-btn>
                        <v-btn color="LightBlue" v-if="!isLast" icon="mdi-arrow-right" class="mx-1"
                            @click="move(1)"></v-btn>
                    </div>
                    <v-sheet class="d-flex mt-auto">
                        <div class="me-auto"></div>
                        <div @click="changeProject(-1)">
                            <Arrow :reversed="true" />
                        </div>
                        <div @click="changeProject(1)">
                            <Arrow />
                        </div>
                    </v-sheet>
                </v-sheet>
                <v-sheet :width="(16 / 9) * sheetHeight" :height="sheetHeight" v-if="project">
                    <v-img aspect-ratio="16/9" cover :src="src" class="show-slider"></v-img>
                    <v-progress-linear color="LightBlue" :model-value="countdown"></v-progress-linear>
                </v-sheet>
            </v-sheet>
            <v-sheet class="d-flex" :height="iconsHeight">
                <v-sheet :width="'21.5vw'" :height="iconsHeight" class="bg-NavyBlue"></v-sheet>
            </v-sheet>
        </v-sheet>
    </div>
</template>

<script>
import Show from "../FileInfo/Show.vue"

export default {
    components: {
        Show,
    },
    props: {
        countdown: {}
    },
    data() {
        return {
            defaultWidth: 416,
            defaultHeight: 532,
            defaultContentHeight: 364,
            defaultSideBarWidth: 58,
            defaultIconsHeight: 68,
            fixedHeight: 0
        }
    },
    computed: {
        src() {
            if (this.project.image_id) {
                return "http://localhost:8080/files?id=" + this.project.image_id; // Ensure to handle logo path correctly
            }
            return "/logo_domyslne.svg"
        },
        multiplier() {
            return this.$store.getters.getMultiplier;
        },
        sheetWidth() {
            return '50vw';
        },
        iconsHeight() {
            return this.defaultIconsHeight * this.multiplier;
        },
        imgWidth() {
            return 'auto';
        },
        isMobile() {
            return this.$store.getters.getIsMobile;
        },
        isMD() {
            return this.$store.getters.getIsMD;
        },
        sheetHeight() {
            if (this.isMD) {
                return 0.7 * this.defaultHeight;
            }
            return this.multiplier * this.defaultHeight;
        },
        contentHeight() {
            return this.multiplier * this.defaultContentHeight;
        },
        sideBarWidth() {
            return this.multiplier * this.defaultSideBarWidth;
        },
        project() {
            return this.$store.getters.getProject;
        },
        projects() {
            return this.$store.getters.getProjects;
        },
        authenticated() {
            return this.$store.getters.getAuthenticated;
        },
        fullWidth() {
            var fullWidth = this.$vuetify.display.width;
            return fullWidth;
        },
        indexProject() {
            return this.projects.indexOf(this.project);
        },
        isFirst() {
            return this.indexProject == 0;
        },
        isLast() {
            return this.indexProject + 1 == this.projects.length;
        }
    },
    methods: {
        src(id) {
            if (id) {
                return `http://localhost:8080/api/files/${id}`;
            }
        },
        move(index) {
            this.$emit("move", this.project, index);
        },
        changeProject(val) {
            this.$emit('changeProject', val);
        },
        goTo() {
            this.$router.push({ name: "Match" });
        },
        async destroy(id) {
            await this.$store.dispatch("deleteProject", id);
            this.$emit("updated");
        },
        setSideMargin() {
            var singleSlider = document.getElementsByClassName('single-slider')[0];
            if (singleSlider) {
                this.$store.commit("setSideMargin", singleSlider.getBoundingClientRect().left);
            }
        },
        setFixedHeight() {
            var sliderContainer = document.getElementsByClassName('slider-container')[0];
            if (sliderContainer) {
                // this.$store.commit("setSideMargin", singleSlider.getBoundingClientRect().left * 0.33)
            }
        }
    },
    mounted() {
        this.setFixedHeight();
    },
}
</script>

<style scoped>
.v-responsive.v-img.show-slider {
    height: 100% !important;
}
</style>
