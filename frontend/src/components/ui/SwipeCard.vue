<template>
    <div v-show="visible" :class="[
        'absolute inset-0 flex items-center justify-center transition-transform duration-700 ease-in-out',
        cardPositionClass,
    ]" @mousedown="startSwipe" @touchstart="startSwipe" @mousemove="onSwipe" @touchmove="onSwipe" @mouseup="endSwipe"
        @touchend="endSwipe" @mouseleave="endSwipe" ref="card">
        <div class="w-full h-full shadow-xl rounded-xl p-6 text-center flex flex-col items-center transform" :style="{
            background: 'linear-gradient(90deg, #5de0e6, #0078a6)', // Gradient background
            transform: `rotate(${rotation}deg) translateX(${translateX}px)`,
            opacity: `${opacity}`,
        }">
            <img :src="src" class="w-40 h-40 mb-4" alt="Logo" />
            <h3 class="text-4xl font-medium text-white">{{ card.name }}</h3>
            <p class="font-medium text-xl py-1 pb-5 text-white">{{ card.history }}</p>
            <div class="flex flex-row flex-wrap justify-center space-x-3">
                <p class="btn-primary w-min font-light px-9 text-white mb-4" v-for="(tag, index) in randomTags">
                    {{ tag.name }}
                </p>
            </div>
            <p class="text-md py-3 pb-8 text-white">
                {{ card.short_about_description }}
            </p>
            <div class="space-y-2 w-full">
                <div class="w-full">
                    <Dropdown :title="'Co chcemy zrobić?'" :value="card.long_about_description" />
                </div>
                <div class="w-full">
                    <Dropdown :title="'Jak chcemy robić?'" :value="card.how_description" />
                </div>
                <div class="w-full">
                    <Dropdown :title="'Czego potrzebujemy?'" :value="card.needs_description" />
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import Dropdown from "../DropodownVuetify.vue";

export default {
    props: {
        card: Object,
        index: Number,
    },
    data() {
        return {
            startX: 0,
            translateX: 0,
            rotation: 0,
            isDragging: false,
            visible: true,
            tags: [
                { id: 1, name: 'Edukacja', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
                { id: 2, name: 'Zdrowie', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
                { id: 3, name: 'Ekologia', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
                { id: 4, name: 'Technologia', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
                { id: 5, name: 'Innowacje', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
                { id: 6, name: 'Ochrona Środowiska', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
                { id: 7, name: 'Kultura', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
                { id: 8, name: 'Sport', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
                { id: 9, name: 'Pomoc Społeczna', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
                { id: 10, name: 'Dzieci', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
                { id: 11, name: 'Seniorzy', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
                { id: 12, name: 'Przedsiębiorczość', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
                { id: 13, name: 'Nauka', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
                { id: 14, name: 'Sztuka', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
                { id: 15, name: 'Zrównoważony Rozwój', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
                { id: 17, name: 'Rolnictwo', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
                { id: 18, name: 'Transport', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
                { id: 19, name: 'Turystyka', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
                { id: 20, name: 'Bezpieczeństwo', createdAt: new Date(), updatedAt: new Date(), deletedAt: null },
            ]

        };
    },
    computed: {
        randomTags() {
            const shuffledTags = [...this.tags].sort(() => 0.5 - Math.random());  // Shuffle the array
            return shuffledTags.slice(0, 6);
        },
        src() {
            console.log(this.card)
            if (this.card?.image_id) {
                return "http://localhost:8080/files?id=" + this.card.image_id; // Ensure to handle logo path correctly
            }
            return "/logo_domyslne.svg"
        },
        cardPositionClass() {
            return this.index === 0 ? "z-10" : "z-0";
        },
        opacity() {
            return Math.min(1, 1 - Math.abs(this.translateX) / 3000);
        },
    },
    components: {
        Dropdown,
    },
    methods: {
        startSwipe(event) {
            this.isDragging = true;
            this.startX =
                event.type === "mousedown" ? event.clientX : event.touches[0].clientX;
        },
        onSwipe(event) {
            if (!this.isDragging) return;
            const currentX =
                event.type === "mousemove" ? event.clientX : event.touches[0].clientX;
            this.translateX = currentX - this.startX;
            this.rotation = (this.translateX / window.innerWidth) * 20;
        },
        endSwipe() {
            if (!this.isDragging) return;
            this.isDragging = false;
            if (Math.abs(this.translateX) > 150) {
                const direction = this.translateX > 0 ? "right" : "left";
                this.$emit("swipe", { card: this.card, direction });
                this.visible = false;
            } else {
                this.translateX = 0;
                this.rotation = 0;
            }
        },
    },
};
</script>
