<template>
    <div @click="handleClickOutside">
        <!-- Main Navbar -->
        <div class="fixed top-0 left-0 w-full z-20 flex items-center h-20 md:h-[85px] mx-auto border-b-2 bg-white">
            <div
                class="flex justify-between items-center w-full max-w-[1400px] mx-auto px-7 rounded-full h-[70px] bg-white">
                <div class="flex items-center w-max">
                    <img :src="hackyeahLogo" class="w-[70px]" @click="goHome" />
                </div>
                <ul class="hidden items-center md:flex space-x-7 text-xs custom:text-sm">
                    <li class="py-2 text-lg cursor-pointer">
                        Witaj {{ actualOrganization.name }}!
                    </li>

                </ul>

                <button @click.stop="handleNavWithEffect" @mouseover="hoverMenu = true" @mouseleave="hoverMenu = false"
                    class="block md:hidden p-3 transition-all duration-300 rounded-3xl"
                    :class="{ 'bg-gray-200': hoverMenu, 'bg-gray-400': clickEffect }">
                    <!-- <img
            :src="barsSolid"
            class="w-[20px] transition-all duration-300 transform"
          /> -->
                </button>
            </div>

            <!-- Mobile Menu Dropdown -->
            <div ref="dropdownMenu"
                class="fixed left-0 w-full bg-white overflow-hidden transition-all duration-500 md:hidden z-10 -mt-[5.5px] border-b"
                :style="{ height: nav ? '280px' : '0', top: '85px' }">
                <ul class="uppercase mt-4 cursor-pointer">
                    <li class="px-4 py-3">Witaj {{ actualOrganization.name }}!</li>

                </ul>
            </div>
        </div>

        <!-- Spacer Div -->
        <div :class="{ 'mt-72': nav, 'mt-0': !nav }" class="transition-all duration-500 ease-in-out"></div>
    </div>
</template>

<script>
import hackyeahLogo from "../assets/hackyeahLogo.png";

export default {
    data() {
        return {
            hackyeahLogo,
            nav: false,
            hoverMenu: false,
            clickEffect: false,
        };
    },
    computed: {
        actualOrganization() {
            return this.$store.getters.getActualOrganization
        }
    },
    methods: {
        goHome() {
            this.$router.push({ name: "Home" })
        },
        handleNavWithEffect() {
            this.nav = !this.nav;
            this.clickEffect = true;

            setTimeout(() => {
                this.clickEffect = false;
            }, 125);
        },
        scrollTo(elementId) {
            const element = document.getElementById(elementId);
            if (element) {
                this.nav = false;
                element.scrollIntoView({ behavior: "smooth" });
            } else {
                console.warn(`Element with ID ${elementId} not found.`);
            }
        },
        handleResize() {
            if (window.innerWidth >= 768) {
                this.nav = false;
            }
        },
        handleClickOutside(event) {
            const dropdownMenu = this.$refs.dropdownMenu;
            if (dropdownMenu && !dropdownMenu.contains(event.target) && this.nav) {
                this.nav = false;
            }
        },
    },
    created() {
        const email = localStorage.getItem('email')
        this.$store.dispatch("getActual", email)
        // console.log("actualOrganization.name")
        // console.log(actualOrganization)
        // if (actualOrganization == null) {
        //     this.$store.commit("setActualOrganization", {})

        // } else {
        //     this.$store.commit("setActualOrganization", actualOrganization)
        // }
    },
    mounted() {
        window.addEventListener("resize", this.handleResize);
        window.addEventListener("click", this.handleClickOutside);
    },
    beforeDestroy() {
        window.removeEventListener("resize", this.handleResize);
        window.removeEventListener("click", this.handleClickOutside);
    },
};
</script>
