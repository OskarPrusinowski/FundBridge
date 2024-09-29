import { createStore } from 'vuex'

import auth from './modules/auth'
import companies from './modules/companies'
import foundations from './modules/foundations'
import projects from './modules/projects'
import files from './modules/files'
import actualOrganization from './modules/actualOrganization'
export default createStore({
    plugins: [
    ],
    modules: {
        auth,
        projects,
        foundations,
        companies,
        files,
        actualOrganization

    },
})
