// Generate a random date between the past year and now
const generateRandomDate = () => {
    const start = new Date();
    start.setFullYear(start.getFullYear() - 1);  // Start from one year ago
    const end = new Date();  // End is the current date
    const randomDate = new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
    return randomDate.toISOString().split('T')[0];  // Format the date as YYYY-MM-DD
};

// Generate a placeholder foundation object
const generatePlaceholderFoundation = (index) => {
    return {
        id: index + 1,  // Assuming ID starts from 1
        email: `foundation${index + 1}@example.com`,
        nip: `1234567890${index + 1}`,  // Random NIP
        name: `Foundation ${index + 1}`,
        description: `This is a description for foundation ${index + 1}.`,
        sector: `Sector ${index + 1}`,
        size: `${Math.floor(Math.random() * 1000)}`,  // Random size
        logoId: null,  // Placeholder for logo ID
        websiteUrl: `http://foundation${index + 1}.com`,
        verified: Math.random() > 0.5,  // Randomly true or false
        date: generateRandomDate(),  // Add a random date
    };
};

// Generate a list of placeholder foundations
const generatePlaceholderFoundations = (count) => {
    return Array.from({ length: count }, (_, index) => generatePlaceholderFoundation(index));
};

// Example of how to use the placeholder data in your state
const placeholderFoundations = generatePlaceholderFoundations(20);



const URL_FOUNDATIONS = "/foundations";

const defaultFoundationParams = {
    sector: "",
    size: "",
};
const defaultFoundationPagination = {
    page: 1,
    size: 5,
};
const defaultFoundation = {
    email: "",
    nip: "",
    name: "",
    description: "",
    sector: "",
    size: "",
    logoId: null,
    websiteUrl: "",
    verified: false,
};

export default {
    state: {
        foundation: { ...defaultFoundation },
        foundations: [],
        foundationsParams: { ...defaultFoundationParams },
        foundationsPagination: { ...defaultFoundationPagination },
        foundationsTotalElements: 0,
        foundationsTotalPages: 0,
    },
    getters: {
        getFoundation: (state) => state.foundation,
        getFoundations: (state) => state.foundations,
        getFoundationsParams: (state) => state.foundationsParams,
        getFoundationsPagination: (state) => state.foundationsPagination,
        getFoundationsTotalElements: (state) => state.foundationsTotalElements,
        getFoundationsTotalPages: (state) => state.foundationsTotalPages,
    },
    mutations: {
        setFoundation(state, data) {
            state.foundation = data;
        },
        initFoundation(state) {
            state.foundation = { ...defaultFoundation };
        },
        setFoundations(state, data) {
            state.foundations = data;
        },
        setFoundationsParams(state, data) {
            state.foundationsParams = data;
        },
        initFoundationsParams(state) {
            state.foundationsParams = { ...defaultFoundationParams };
        },
        setFoundationsPagination(state, data) {
            state.foundationsPagination = data;
        },
        initFoundationsPagination(state) {
            state.foundationsPagination = { ...defaultFoundationPagination };
        },
        setFoundationsTotalElements(state, data) {
            state.foundationsTotalElements = data;
        },
        setFoundationsTotalPages(state, data) {
            state.foundationsTotalPages = data;
        },
    },
    actions: {
        async registerFundation({commit,getters}){
            const foundation = getters.getFoundation
            await axios.post(`${URL_FOUNDATIONS}/register`,foundation).then((response) => {
                console.log(response);
                const data = response.data;
                commit("setFoundation", data);
            });
        },
        async listFoundations({ commit, getters }) {
            let params = getters.getFoundationsParams;
            const pagination = getters.getFoundationsPagination;
            params.size = pagination.size;
            params.page = pagination.page - 1;
            await axios.get(URL_FOUNDATIONS, { params: params }).then((response) => {
                console.log(response);
                const data = response.data;
                console.log(data);
                console.log(data)
                commit("setFoundations", data);
                // commit("setFoundations", placeholderFoundations);
                commit("setFoundationsTotalPages", response.data.totalPages);
                commit("setFoundationsTotalElements", response.data.totalElements);
            });
        },
        getFoundation({ commit }, id) {
            axios
                .get(`${URL_FOUNDATIONS}/${id}`)
                .then((response) => {
                    console.log(response);
                    const foundation = response.data;
                    commit("setFoundation", foundation);
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        createFoundation({ getters, commit }) {
            const foundation = getters.getFoundation;
            console.log(foundation);
            return axios.post(URL_FOUNDATIONS, foundation);
        },
        async deleteFoundation({ }, id) {
            await axios.delete(`${URL_FOUNDATIONS}/${id}`).then((response) => {
                console.log(response);
            });
        },
    },
};
