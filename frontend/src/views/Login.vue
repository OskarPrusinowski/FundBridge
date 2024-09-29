<template>
    <div id="contact-section" class="flex md:items-center md:justify-center min-h-screen mt-20 md:mt-0">
        <div class="max-w-[500px] w-full mx-8">
            <div class="flex flex-col text-center text-black">
                <!-- Added text-black -->
                <div class="text-2xl font-semibold mb-1 text-left">Logowanie</div>
                <div class="gap-x-12 text-sm text-left font-medium text-[#63748D]">
                    do panelu firmy/organizacji NGO
                </div>
            </div>
            <div class="w-full py-10">
                <div class="max-w-[950px]">
                    <div class="flex flex-col mb-5 md:mb-0">
                        <div class="text-sm font-medium text-[#334155]">E-mail</div>
                        <input type="text" id="full-name" placeholder="Wpisz e-mail"
                            class="mt-1 p-2 border rounded-md h-[54px] mb-6 bg-white" v-model="email" />
                    </div>
                    <div class="flex flex-col">
                        <div class="text-sm -mt-2 font-medium text-[#334155]">Hasło</div>
                        <input type="password" id="password" placeholder="Wpisz hasło"
                            class="mt-1 p-2 border rounded-md h-[54px] bg-white" />
                    </div>

                    <div class="pt-3">
                        <div @click="login"
                            class="btn-primary mx-auto flex justify-center mt-8 text-[18px] max-w-[600px] bg-[#08446C] w-full rounded-lg h-[60px] hover:bg-blue-950">
                            Zaloguj się
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import email from "../assets/email.png";
import password from "../assets/password.png";

export default {
    data() {
        return {
            email,
            password,
        };
    },
    methods: {
        login() {
            this.$store.dispatch("login", this.email)
        }
    }
};
</script>
<style>
input {
    border: 1px solid #d1d5db;
}

input:focus {
    border-color: #d1d5db;
    outline: none;
}
</style>
