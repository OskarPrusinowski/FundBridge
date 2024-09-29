import './style.css'
// src/main.js
import '@mdi/font/css/materialdesignicons.css';

import { createApp } from 'vue'
import App from './App.vue'
import store from './store'
import router from './routes.js'
import "./bootstrap.js"

import moment from 'moment'


import { aliases, mdi } from 'vuetify/lib/iconsets/mdi'

// Roboto & Material Icons Fonts
import '@mdi/font/css/materialdesignicons.css'
import 'roboto-fontface/css/roboto/roboto-fontface.css'
import { createVuetify } from 'vuetify'
import customThemes from './plugins/colors.ts'
import 'vuetify/styles'

const customLightTheme = customThemes.customLightTheme;
const customContrastTheme = customThemes.customContrastTheme;
const customHighestContrastTheme = customThemes.customHighestContrastTheme

const vuetify = createVuetify({
    theme: {
        defaultTheme: 'customLightTheme',
        themes: {
            customLightTheme,
            customContrastTheme,
            customHighestContrastTheme
        }
    }
})


const app = createApp(App)

import Pagination from "../src/components/ui/table/Pagination.vue"
import GoBack from "../src/components/ui/navigation/GoBack.vue"
import SliderProgress from "../src/components/ui/newsroom/SliderProgress.vue"
import Arrow from "../src/components/ui/newsroom/Arrow.vue"
import RedirectBtn from "../src/components/ui/buttons/RedirectBtn.vue"
import ToolTip from "../src/components/ui/buttons/ToolTip.vue"
import RangeDatePicker from "../src/components/ui/inputs/RangeDatePicker.vue"
import TextInput from "../src/components/ui/inputs/TextInput.vue"
import SearchLoop from "../src/components/ui/inputs/SearchLoop.vue"
import DestroyDialog from "../src/components/ui/dialogs/DestroyDialog.vue"

app.component('ToolTip', ToolTip)
app.component('Pagination', Pagination);
app.component('GoBack', GoBack);
app.component('SliderProgress', SliderProgress);
app.component('Arrow', Arrow);
app.component('RedirectBtn', RedirectBtn);
app.component('TextInput', TextInput);
app.component('RangeDatePicker', RangeDatePicker);
app.component('SearchLoop', SearchLoop);
app.component('DestroyDialog', DestroyDialog)

app.config.globalProperties.$moment = moment;

app.use(store)
app.use(router)
app.use(vuetify)
app.mount('#app')
