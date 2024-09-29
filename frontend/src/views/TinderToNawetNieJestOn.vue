<template>
    <div class="flex items-center justify-center h-screen mt-16">
        <div class="relative h-[760px] w-[660px] mt-16">
            <SwipeCard v-for="(card, index) in projects" :key="card.id" :card="card" :index="index"
                @swipe="handleSwipe" />
        </div>
    </div>
</template>

<script>
import SwipeCard from "../components/ui/SwipeCard.vue";
import hackyeahLogo from "../assets/hackyeahLogoNoBg.png";
export default {
    components: {
        SwipeCard,
    },
    data() {
        return {
            cards: [
                {
                    id: 1,
                    name: "Sigma Projekt",
                    bio: "Green Space",
                    tags: [
                        "Ochrona środowiska",
                        "Edukacja",
                        "Globalne ocieplenie",
                        "10000$-20000$",
                        "Wsparcie finansowe",
                        "Organizacja non-profit",
                    ],
                    description:
                        "Green Space to organizacja non-profit zajmująca się ochroną środowiska i edukacją. Naszą misją jest walka z globalnym ociepleniem poprzez innowacyjne programy i zaangażowanie społeczności. Koncentrujemy się na tworzeniu zrównoważonych rozwiązań, które umożliwiają jednostkom i społecznościom podejmowanie działań na rzecz bardziej ekologicznej przyszłości.",
                    questions: [
                        "Jesteśmy organizacją non-profit zajmującą się ochroną środowiska i edukacją.",
                        "Chcemy walczyć z globalnym ociepleniem poprzez innowacyjne programy edukacyjne.",
                        "Planujemy organizować warsztaty i kampanie informacyjne angażujące społeczność.",
                        "Potrzebujemy wsparcia finansowego i zaangażowania lokalnych społeczności.",
                    ],
                    logo: hackyeahLogo,
                },
                { id: 2, name: "Jane Smith", bio: "Graphic Designer" },
                { id: 3, name: "Chris Lee", bio: "Photographer" },
            ],
        };
    },
    computed: {
        projects() {
            return this.$store.getters.getProjects;
        },
    },
    methods: {
        handleSwipe({ card, direction }) {
            console.log(`${card.name} swiped ${direction}`);
            this.cards = this.cards.filter((c) => c.id !== card.id);
            if (this.cards.length === 0) {
                this.loadMoreCards();
            }
        },
        loadMoreCards() {
            this.cards = [
                { id: 4, name: "Michael Brown", bio: "Product Manager" },
                { id: 5, name: "Emma Wilson", bio: "Marketing Expert" },
                { id: 6, name: "Emma Wilson", bio: "Marketing Expert" },
                { id: 7, name: "Emma Wilson", bio: "Marketing Expert" },
                { id: 8, name: "Emma Wilson", bio: "Marketing Expert" },
                { id: 9, name: "Emma Wilson", bio: "Marketing Expert" },
                { id: 10, name: "Emma Wilson", bio: "Marketing Expert" },
                { id: 11, name: "Emma Wilson", bio: "Marketing Expert" },
                { id: 12, name: "Emma Wilson", bio: "Marketing Expert" },
            ];
        },
        listProjects() {
            this.$store.dispatch("listProjects")
        }
    },
    created() {
        this.listProjects();
    },
};
</script>
