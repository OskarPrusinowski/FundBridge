<template>
    <div>
        <div class="d-flex align-center " @click="goBack">
            <div>
                <Arrow :reversed="true" />
            </div>
            <div :class="[isMobile ? 'cml-4' : 'cml-8']" class="title clickable text-DarkBlue c-text-1">
                {{ label }}
            </div>
        </div>
        <SliderProgress class="cmt-8" />
    </div>
</template>
<script>
export default {
    props: ["routeName", "label"],
    methods: {
        goBack() {
            this.$router.push({ name: this.routeName })
        }
    },
    computed: {
        isMobile() {
            return this.$store.getters.getIsMobile
        }
    }
}
</script>