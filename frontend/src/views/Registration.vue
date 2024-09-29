<template>
  <div
    id="contact-section"
    class="flex md:items-center md:justify-center min-h-screen mt-20 md:mt-0"
  >
    <div class="max-w-[500px] w-full mx-8">
      <div class="flex flex-col text-center text-black">
        <div class="text-xl font-semibold mb-1 text-left">Zarejestruj się</div>
        <div class="mb-8 gap-x-12 font-medium text-xs text-left">
          Utwórz konto dla swojej firmy lub organizacji
        </div>

        <div class="flex w-full mb-6 h-[38px] rounded-md border">
          <button
            @click="isCompany = true"
            :class="[
              'flex-1 rounded-l-xl transition-colors duration-300 text-sm md:text-base',
              isCompany
                ? 'bg-[#08446C] text-white '
                : 'bg-white text-black hover:text-white hover:bg-blue-950',
            ]"
          >
            Rejestracja firmy
          </button>
          <button
            @click="isCompany = false"
            :class="[
              'flex-1 rounded-r-xl transition-colors duration-300 text-sm md:text-base',
              !isCompany
                ? 'bg-[#08446C] text-white '
                : 'bg-white text-black hover:text-white hover:bg-blue-950',
            ]"
          >
            Rejestracja NGO
          </button>
        </div>
      </div>

      <div class="w-full py-10">
        <div class="max-w-[950px]">
          <div class="flex flex-col mb-5 md:mb-0">
            <div class="font-medium">Nazwa firmy*</div>
            <input
              type="text"
              id="full-name"
              placeholder="Nazwa firmy"
              class="mt-1 p-2 border rounded-md h-[54px] mb-6 text-[14px] pl-[10px] placeholder-[#64748B] bg-white"
            />
          </div>
          <div class="flex flex-col mb-5 md:mb-0">
            <div class="font-medium">Numer NIP</div>
            <input
              type="text"
              id="full-name"
              placeholder="Numer NIP"
              class="mt-1 p-2 border rounded-md h-[54px] mb-6 text-[14px] pl-[10px] placeholder-[#64748B] bg-white"
            />
          </div>
          <div class="font-medium mb-2">Rozmiar firmy</div>
          <Dropdown
            class="mb-7"
            :options="companySizes"
            :defaultOption="'Wybierz rozmiar'"
          />
          <div class="flex flex-col mb-5 md:mb-0">
            <div class="font-medium">Hasło*</div>
            <input
              type="text"
              id="full-name"
              placeholder="Wpisz hasło"
              class="mt-1 p-2 border rounded-md h-[54px] mb-6 text-[14px] pl-[10px] placeholder-[#64748B] bg-white"
            />
          </div>
          <div class="font-medium mb-2">Wybierz sektor</div>
          <Dropdown
            class="mb-7"
            :options="sectors"
            :defaultOption="'Wybierz sektor'"
          />
          <div class="flex items-center space-x-2 pt-6 pl-2">
            <input
              type="checkbox"
              id="tos"
              class="form-checkbox h-6 w-6 text-blue-600 border-gray-300 rounded focus:ring-blue-500"
            />
            <label for="tos" class="text-gray-700">
              Zapoznałem/am się z powyższymi informacjami
              <a href="#" class="text-blue-600 hover:underline"></a>
            </label>
          </div>
          <div class="pt-3">
            <div
              class="btn-primary mx-auto flex justify-center mt-8 max-w-[600px] w-full rounded-md hover:bg-blue-950"
            >
              Utwórz konto
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
import Dropdown from "../components/Dropdown.vue";

export default {
  data() {
    return {
      isCompany: true,
      email,
      password,
      companySizes: ["Mała", "Średnia", "Duża"],
      sectors: ["1", "2", "3"],
    };
  },
  components: {
    Dropdown,
  },
  methods: {},
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
