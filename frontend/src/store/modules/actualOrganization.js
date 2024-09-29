import router from "../../routes";


const URL_ORGANIZATIONS = "/organizations";

const defaultActualOrganization = {
    id: null,
    name: "",
    description: "",
    email:""
    // Add more fields as needed
};


export default {
    state: {
        actualOrganization: { ...defaultActualOrganization },
    },
    getters: {
        getActualOrganization: (state) => state.actualOrganization,
    },
    mutations: {
        setActualOrganization(state, data) {
            state.actualOrganization = data;
        },
    },
    actions: {
        async login({commit,getters},email){
            await axios.post(`organizations/login?email=${email}&password=trudnehaslo`,{email:email,password:"password123"}).then((response) => {
                console.log(response);
                const data = response.data;
                commit("setActualOrganization", data);
                localStorage.setItem('actualOrganization', data)
                localStorage.setItem('email', data.email)
                router.push({name:"Home"})
            });
        },
        async getActual({commit,getters},email){
            await axios.post(`organizations/login?email=${email}&password=trudnehaslo`,{email:email,password:"password123"}).then((response) => {
                console.log(response);
                const data = response.data;
                commit("setActualOrganization", data);
            });
        },
    },
};
