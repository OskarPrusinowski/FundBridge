<template>
    <div class="mx-auto">
        <v-sheet class="d-flex flex-column justify-centers mb- mx-auto mt-11" style="width:80%;">
            <Slider />
            <NewsRoom />
        </v-sheet>
    </div>
</template>
<script>
import Slider from "./Slider/Slider.vue";
import NewsRoom from "./Foundations/NewsRoom.vue";
export default {
    components: {
        NewsRoom,
        Slider,
    },
};
</script>
