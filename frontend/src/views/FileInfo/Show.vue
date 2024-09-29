<template>
    <div v-if="authenticated && manageable">
        <v-badge v-if="addBadge" color="DarkBlue">
            <v-hover>
                <template v-slot:default="{ isHovering, props }">
                    <v-sheet v-bind="props">
                        <v-img v-if="path || directSrc" :src="directSrc ? directSrc : src" :width="dividedWidth"
                            :height="dividedHeight" @click="show" class="clickable">
                            <v-fade-transition>
                                <div v-if="isHovering && manageable"
                                    class="d-flex transition-fast-in-fast-out d-flex v-card--reveal bg-grey-lighten-5 justify-space-around align-end pb-3"
                                    :style="'opacity:50%;'" elevation="10" style="height: 100%;">
                                    <v-btn @click="destroy" size="x-small" icon="mdi-trash-can"></v-btn>
                                </div>
                            </v-fade-transition>
                        </v-img>
                    </v-sheet>
                </template>
            </v-hover>
        </v-badge>
        <v-hover v-else>
            <template v-slot:default="{ isHovering, props }">
                <v-sheet v-bind="props">
                    <v-img v-if="path || directSrc" :src="directSrc ? directSrc : src" :width="dividedWidth"
                        :height="dividedHeight" @click="show" class="clickable">
                        <v-fade-transition>
                            <div v-if="isHovering && manageable"
                                class="d-flex transition-fast-in-fast-out d-flex v-card--reveal bg-grey-lighten-5 justify-space-around align-end pb-3"
                                :style="'opacity:50%;'" elevation="10" style="height: 100%;">
                                <v-btn @click="set" size="x-small" icon="mdi-star-circle"></v-btn>
                                <v-btn @click="destroy" size="x-small" icon="mdi-trash-can"></v-btn>
                            </div>
                        </v-fade-transition>
                    </v-img>
                </v-sheet>
            </template>
        </v-hover>
    </div>
    <div v-else>
        <v-img v-if="path || directSrc" :src="directSrc ? directSrc : src" :width="dividedWidth" :height="dividedHeight"
            @click="show" class="clickable"></v-img>
    </div>
</template>
<script>
export default {
    props: {
        path: {},
        id: {},
        directSrc: {},
        addBadge: { default: false },
        width: { default: 120 },
        height: { default: 80 },
        manageable: { default: true },
        divide: { default: false }
    },
    data() {
        return {
            setted: false,
            defaultWidth: 491,
            defaultHeight: 337
        }
    },
    computed: {
        src() {
            if (this.id) {
                return `http://localhost:8080/api/files/${this.id}`;
            }
            return `http://localhost:8080/api/files?path=${this.path}`;
        },
        authenticated() {
            return this.$store.getters.getAuthenticated
        },
        multiplier() {
            return this.$store.getters.getMultiplier;
        },
        isXS() {
            return this.$store.getters.getIsXS;
        },
        isMD() {
            return this.$store.getters.getIsMD;
        },
        isMobile() {
            return this.$store.getters.getIsMobile
        },
        divideInto() {
            if (this.isXS) {
                return 1;
            } else if (this.isMobile) {
                return 2;
            }
            return 3;
        },
        markedContainerWidth() {
            if (document.getElementsByClassName('marked-container')[0]) {
                return document.getElementsByClassName('marked-container')[0].clientWidth;
            }
            return 0;
        },
        dividedWidth() {
            if (!this.divide) {
                if (this.width && this.width != 120 && this.width != 0) {
                    return this.width
                }
                if (this.height != 80) {
                    return "auto"
                }
                return this.multiplier * this.defaultWidth
            } else {
                return (this.markedContainerWidth / this.divideInto) * 0.9;
            }
        },
        dividedHeight() {
            if (this.height != 80) {
                return this.height;
            }
            return this.dividedWidth * (this.defaultHeight / this.defaultWidth);
        }
    },
    methods: {
        show() {
            if (!this.setted) {
                this.$emit("show")
            }
            this.setted = false
        },
        destroy() {
            this.$emit("destroy")
        },
        set() {
            this.setted = true
            this.$emit("set")
        },
    }
}
</script>