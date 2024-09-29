<template>
    <div class="mx-auto">
        <Single v-if="!isMobile" @changeProject="changeProject" @updated="listProjects" @move="move"
            :countdown="(100 - countdownTime / 90)" />
        <MobileSingle v-else @changeProject="changeProject" />
        <v-pagination v-if="authenticated && !isMobile" v-model="index" :length="projects.length"
            @update:modelValue="update"></v-pagination>
        <v-sheet class="actions d-flex cma-4" max-width="1000">
            <v-btn v-if="authenticated && !isMobile" icon="mdi-plus" @click="open" class="cma-2"></v-btn>
        </v-sheet>
    </div>
</template>

<script>
import Single from './Single.vue';
import MobileSingle from './MobileSingle.vue';

export default {
    components: { Single, MobileSingle, },
    data() {
        return {
            index: 1,
            intervalId: null,
            change: true,
            loading: false,
            loadingProgress: 0,
            countdownTime: 9000,
            countdownIntervalId: null,
        };
    },
    computed: {
        isMobile() {
            return this.$store.getters.getIsMobile;
        },
        projects() {
            return this.$store.getters.getProjects.sort((a, b) => a.number - b.number);
        },
        authenticated() {
            return false;
        },
    },
    methods: {
        async move(oldProjectElement, val) {
            var newProjectElement = this.getByIndex(val + this.index);
            var tempNumber = newProjectElement.number;
            newProjectElement.number = oldProjectElement.number;
            oldProjectElement.number = tempNumber;
            var projects = [newProjectElement, oldProjectElement];
            await this.$store.dispatch("updateAllProjects", projects);
            this.update(1);
        },
        getByIndex(index) {
            return this.projects[(index - 1 + this.projects.length) % this.projects.length];
        },
        startAutoPlay() {
            this.resetCountdown();
        },
        resetCountdown() {
            clearInterval(this.countdownIntervalId);
            this.countdownTime = 9000;
            this.countdownIntervalId = setInterval(() => {
                if (this.countdownTime > 0) {
                    this.countdownTime -= 100;
                } else {
                    this.countdownTime = 9000;
                    this.update(this.index + 1);
                }
            }, 100);
        },
        stopAutoPlay() {
            clearInterval(this.intervalId);
            clearInterval(this.countdownIntervalId);
        },
        async listProjects() {
            await this.$store.dispatch("listProjects");
            this.update(1);
        },
        changeProject(val) {
            this.update(this.index + val);
        },
        update(val) {
            if (this.$route.name != 'Home') {
                this.stopAutoPlay();
            } else {
                if (val == 0) {
                    this.index = this.projects.length;
                } else if (val == this.projects.length + 1) {
                    this.index = 1;
                } else {
                    this.index = val;
                }
                this.resetInterval();
                this.$store.commit("setProject", this.projects[this.index - 1]);
            }
        },
        setSliderHeight() {
            var singleSlider = document.getElementsByClassName('show-slider')[0];
            if (singleSlider) {
                this.$store.commit("setSliderHeight", singleSlider.getBoundingClientRect().height * 0.75);
            }
        },
        resetInterval() {
            this.stopAutoPlay();
            this.startAutoPlay();
        },
    },
    async created() {
        await this.listProjects();
        this.update(1);
    },
    beforeDestroy() {
        this.stopAutoPlay();
    },
};
</script>
