import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import vuetify from 'vite-plugin-vuetify'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [vue({
    template: {
        transformAssetUrls: {
            base: null,
            includeAbsolute: false,
        },
    },
}),,
    vuetify({
      autoImport: true, // Automatically imports Vuetify components
    }),],
})
