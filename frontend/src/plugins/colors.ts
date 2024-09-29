import { ThemeDefinition } from 'vuetify'
import '@mdi/font/css/materialdesignicons.css'

const customLightTheme: ThemeDefinition = {
    dark: false,
    colors: {
        "LightBlue": "#91C8DF",
        "DarkBlue": "#184773",
        "NavyBlue": "#054169",
        "LightGray": "#F4F4F4",
        "Gray": "#A3A3A3",
        "DarkerBlue": "#0B1E37",
        "SkyBlue": "#92C9DE",
        "PoliceBlue": "#404D5D",
        "white": "#fff",
        "AntiFlashWhite": "#EFF1F5",
        "Crimson": "#DB153C",
        "DarkSilver": "#6F6F6F"
    }
}

const customContrastTheme: ThemeDefinition = {
    dark: false,
    colors: {
        "LightBlue": "#1945FF",
        "DarkBlue": "#184773",
        "NavyBlue": "#1945FF",
        "LightGray": "#F4F4F4",
        "Gray": "#A3A3A3",
        "DarkerBlue": "#0B1E37",
        "SkyBlue": "#92C9DE",
        "PoliceBlue": "#404D5D",
        "AntiFlashWhite": "#EFF1F5",
        "white": "#fff",
        "Crimson": "#DB153C",
        "DarkSilver": "#6F6F6F"
    }
}


const customHighestContrastTheme: ThemeDefinition = {
    dark: false,
    colors: {
        "LightBlue": "#31C2FF",
        "DarkBlue": "#FFFF00",
        "NavyBlue": "#FFFF00",
        "LightGray": "#F4F4F4",
        "Gray": "#A3A3A3",
        "DarkerBlue": "#000",
        "SkyBlue": "#92C9DE",
        "PoliceBlue": "#404D5D",
        "AntiFlashWhite": "#EFF1F5",
        "white": "#000",
        "background": "#000",
        "Crimson": "#DB153C",
        "DarkSilver": "#6F6F6F"
    }
}

const customThemes = { customLightTheme: customLightTheme, customContrastTheme: customContrastTheme, customHighestContrastTheme: customHighestContrastTheme }

export default customThemes;
