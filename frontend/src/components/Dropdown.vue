<template>
  <div class="relative mb-3">
    <button
      @click="toggleDropdown"
      class="h-[55px] w-full py-2 px-4 border rounded-md bg-white flex justify-between items-center text-sm text-[#64748B]"
    >
      <span>{{ selectedOption }}</span>
      <svg
        class="w-5 h-5"
        fill="none"
        stroke="currentColor"
        viewBox="0 0 24 24"
        xmlns="http://www.w3.org/2000/svg"
      >
        <path
          stroke-linecap="round"
          stroke-linejoin="round"
          stroke-width="2"
          d="M6 9l6 6 6-6"
        />
      </svg>
    </button>
    <div
      v-if="isDropdownOpen"
      class="absolute z-10 w-full mt-1 bg-white border rounded-md shadow-lg"
    >
      <ul>
        <li
          v-for="option in options"
          :key="option"
          @click="selectOption(option)"
          class="py-2 px-4 hover:bg-gray-200 cursor-pointer"
        >
          {{ option }}
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    options: {
      type: Array,
      required: true, // Ensure that options are provided
    },
    defaultOption: {
      type: String,
      default: "Rozmiar firmy", // Default value if not specified
    },
  },
  data() {
    return {
      selectedOption: this.defaultOption, // Initialize with the default option
      isDropdownOpen: false, // State to manage dropdown visibility
    };
  },
  methods: {
    toggleDropdown() {
      this.isDropdownOpen = !this.isDropdownOpen; // Toggle dropdown visibility
    },
    selectOption(option) {
      this.selectedOption = option; // Set the selected option
      this.isDropdownOpen = false; // Close the dropdown after selection
    },
  },
};
</script>

<style scoped>
/* You can add styles here if necessary */
</style>
