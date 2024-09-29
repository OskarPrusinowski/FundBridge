import store from '..';
import router from '../../routes'
const URL_AUTH = '/users';
const ADMIN_CODE = "XXXXXX"

export default {
    state: {
        isAuthenticated: false,
        actualUser: {
            roles: []
        },
        roles: []
    },
    getters: {
        getIsAuthenticated: state => state.isAuthenticated,
        getActualUser: state => state.actualUser,
        getActualUserRoles: state => state.actualUser.roles
    },
    mutations: {
        setIsAuthenticated(state, data) {
            state.isAuthenticated = data;
        },
        setActualUser(state, value) {
            state.actualUser = value;
        },
        setActualUserRoles(state, data) {
            state.actualUser.roles = data;
        },
        initActualUser(state) {
            state.actualUser = { roles: [] }
        }
    },
    actions: {
        async getActualUser(state) {
            await axios.get(URL_AUTH + '/get-actual').then(({ data }) => {

                state.commit("setActualUser", data);
                state.commit("setIsAuthenticated", true);
                var modulesParams = store.getters.getModulesParams
                if (data.roles.length === 0) {
                    modulesParams.roleId = 1
                } else {
                    modulesParams.roleId = data.roles[0].id
                }
                const roles = data.roles.map(role => role.name);
                localStorage.setItem('roles', roles)
                store.commit("setModulesParams", modulesParams)
                store.dispatch("listModules")
            }).catch(error => {
                console.log(error)
                this.dispatch("logout")
            })
        },
        async getRefreshToken(state) {
            const refresh_token = localStorage.getItem('refresh_token')
            await axios.post(URL_AUTH + '/refresh-token', { refreshToken: refresh_token }).then((response) => {
                const access_token = response.data.accessToken
                localStorage.setItem('access_token', access_token)
                this.dispatch("getActualUser")
            }).catch(() => {
                state.commit("initActualUser", {});
                state.commit("setIsAuthenticated", false);
            })
        },
        async logout(state) {
            await axios.post(URL_AUTH + '/logout').then(() => {
                state.commit("setActualUser", {});
                state.commit("setIsAuthenticated", false);
                localStorage.removeItem('access_token')
                localStorage.removeItem('roles')
                router.push({ name: 'Login' })
            })
        },
        async sendResetPassword(state, email) {
            return axios.post(URL_AUTH + '/sendPasswordReset?email=' + email)
        },
        async resetPassword(state, { token, password }) {
            return axios.post(URL_AUTH + '/resetPassword?token=' + token + '&password=' + password)
        }
    }
}
