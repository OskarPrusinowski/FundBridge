const URL_COMPANIES = "/companies";

const defaultCompanyParams = {
    sector: "",
    size: "",
    isOpenToWork: null,
    minBudget: null,
    maxBudget: null,
};
const defaultCompanyPagination = {
    page: 1,
    size: 5,
};
const defaultCompany = {
    email: "",
    nip: "",
    name: "",
    description: "",
    sector: "",
    size: "",
    logoId: null,
    websiteUrl: "",
    verified: false,
    isOpenToWork: false,
    minBudget: null,
    maxBudget: null,
};

export default {
    state: {
        company: { ...defaultCompany },
        companies: [],
        companiesParams: { ...defaultCompanyParams },
        companiesPagination: { ...defaultCompanyPagination },
        companiesTotalElements: 0,
        companiesTotalPages: 0,
        isMobile:false,
        multiplier:1
    },
    getters: {
        getCompany: (state) => state.company,
        getCompanies: (state) => state.companies,
        getCompaniesParams: (state) => state.companiesParams,
        getCompaniesPagination: (state) => state.companiesPagination,
        getCompaniesTotalElements: (state) => state.companiesTotalElements,
        getCompaniesTotalPages: (state) => state.companiesTotalPages,
        getIsMobile: state => state.isMobile,
        getMultiplier:state=>state.multiplier
    },
    mutations: {
        setCompany(state, data) {
            state.company = data;
        },
        initCompany(state) {
            state.company = { ...defaultCompany };
        },
        setCompanies(state, data) {
            state.companies = data;
        },
        setCompaniesParams(state, data) {
            state.companiesParams = data;
        },
        initCompaniesParams(state) {
            state.companiesParams = { ...defaultCompanyParams };
        },
        setCompaniesPagination(state, data) {
            state.companiesPagination = data;
        },
        initCompaniesPagination(state) {
            state.companiesPagination = { ...defaultCompanyPagination };
        },
        setCompaniesTotalElements(state, data) {
            state.companiesTotalElements = data;
        },
        setCompaniesTotalPages(state, data) {
            state.companiesTotalPages = data;
        },
    },
    actions: {
        async listCompanies({ commit, getters }) {
            let params = getters.getCompaniesParams;
            const pagination = getters.getCompaniesPagination;
            params.size = pagination.size;
            params.page = pagination.page - 1;
            await axios.get(URL_COMPANIES, { params: params }).then((response) => {
                console.log(response);
                const data = response.data.content;
                console.log(data);
                commit("setCompanies", data);
                commit("setCompaniesTotalPages", response.data.totalPages);
                commit("setCompaniesTotalElements", response.data.totalElements);
            });
        },
        getCompany({ commit }, id) {
            axios
                .get(`${URL_COMPANIES}/${id}`)
                .then((response) => {
                    console.log(response);
                    const company = response.data;
                    commit("setCompany", company);
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        createCompany({ getters, commit }) {
            const company = getters.getCompany;
            console.log(company);
            return axios.post(URL_COMPANIES, company);
        },
        async deleteCompany({ }, id) {
            await axios.delete(`${URL_COMPANIES}/${id}`).then((response) => {
                console.log(response);
            });
        },
    },
};
