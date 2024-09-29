<template>
    <div id="company-info-section"
        class="flex md:items-center md:justify-center min-h-screen mt-20 md:-mt-12 bg-gray-100">
        <div class="max-w-[500px] w-full mx-8 bg-white shadow-lg rounded-xl p-6">
            <div class="flex flex-col text-center text-black">
                <div class="text-2xl font-semibold mb-1 text-left">
                    {{ foundation.name }}
                </div>
                <div class="mb-2 text-sm text-left">
                    {{ foundation.goals }}
                </div>
            </div>

            <div class="w-full py-6">
                <div class="flex flex-col mb-4">
                    <div class="text-sm">E-mail:</div>
                    <div class="font-medium text-black"> {{ foundation.email }}
                    </div>
                </div>
                <div class="flex flex-col mb-4">
                    <div class="text-sm">Strona internetowa:</div>
                    <a :href="foundation.websiteUrl" class="font-medium text-blue-600 hover:underline" target="_blank"
                        rel="noopener noreferrer">
                        {{ foundation.websiteUrl }}
                    </a>
                </div>
                <div class="flex flex-col mb-4">
                    <div class="text-sm">NIP:</div>
                    <div class="font-medium text-black">
                        {{ foundation.nip }}
                    </div>
                </div>
                <div class="flex flex-col mb-4">
                    <div class="text-sm">Wielkość:</div>
                    <div class="font-medium text-black">
                        {{ foundation.size }}
                    </div>
                </div>
                <div class="flex flex-col mb-4">
                    <div class="text-sm">Budżet:</div>
                    <div class="font-medium text-black">
                        Min: {{ formatCurrency(minBudget) }} - Max:
                        {{ formatCurrency(maxBudget) }}
                    </div>
                </div>
                <div class="flex flex-col mb-4">
                    <div class="text-sm">Otwartość na współpracę:</div>
                    <div class="font-medium text-black">
                        {{ foundation.isOpenToWork ? "Tak" : "Nie" }}
                    </div>
                </div>
                <div class="flex flex-col mb-4">
                    <div class="text-sm">Zweryfikowana firma:</div>
                    <div class="font-medium text-black">
                        {{ foundation.verified ? "Tak" : "Nie" }}
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    data() {
        return {
            name: "Tech Innovators Inc.",
            description:
                "Firma zajmująca się tworzeniem innowacyjnych rozwiązań technologicznych. Nie wszyscy rodzą się empatyczni.",
            email: "contact@techinnovators.com",
            websiteUrl: "https://www.techinnovators.com/",
            nip: "1234567890",
            size: "MEDIUM",
            minBudget: 10000.0,
            maxBudget: 500000.0,
            isOpenToWork: true,
            verified: false,
        };
    },
    computed: {
        foundation() {
            return this.$store.getters.getFoundation;
        },
    },
    methods: {
        formatCurrency(value) {
            return new Intl.NumberFormat("pl-PL", {
                style: "currency",
                currency: "PLN",
            }).format(value);
        },
        getFoundation() {
            this.$store.dispatch("getFoundation", this.$route.params.id)
        }
    },
    created() {
        this.getFoundation()
    }
};
</script>

<style scoped>
#company-info-section {
    background-color: #f9fafb;
    /* Light gray background for contrast */
}
</style>
