const generateRandomDate = () => {
    const start = new Date();
    start.setFullYear(start.getFullYear() - 1);  // Start from one year ago
    const end = new Date();  // End is the current date
    const randomDate = new Date(start.getTime() + Math.random() * (end.getTime() - start.getTime()));
    return randomDate.toISOString().split('T')[0];  // Format the date as YYYY-MM-DD
};

const generatePlaceholderProject = (index) => {
    return {
        id: index + 1,  // Assuming ID starts from 1
        name: `Project ${index + 1}`,
        description: `This is a description for project ${index + 1}.`,
        logoId: null,  // Assuming no logo for placeholder
        amount: Math.floor(Math.random() * 10000),  // Random amount between 0 and 9999
        lookingForText: `Looking for collaborators for project ${index + 1}.`,
        date: generateRandomDate(),  // Add a random date for the project
    };
};

const generatePlaceholderProjects = (count) => {
    return Array.from({ length: count }, (_, index) => generatePlaceholderProject(index));
};

const placeholderProjects = generatePlaceholderProjects(5);  // Generate 10 placeholder projects

const URL_PROJECTS = "/projects";

const defaultProjectParams = {
    name: "",
    description: "",
    logoId: null,
    amount: 0,
    lookingForText: "",
    date: "",  // Add date to default project params
};

const defaultProjectPagination = {
    page: 1,
    size: 5,
};

const defaultProject = { ...defaultProjectParams };

export default {
    state: {
        project: { ...defaultProject },
        projects: placeholderProjects,  // Use placeholder data for initial state
        projectsParams: { ...defaultProjectParams },
        projectsPagination: { ...defaultProjectPagination },
        projectsTotalElements: placeholderProjects.length,  // Total elements in placeholder data
        projectsTotalPages: Math.ceil(placeholderProjects.length / defaultProjectPagination.size),  // Calculate total pages
    },
    getters: {
        getProject: (state) => state.project,
        getProjects: (state) => state.projects,
        getProjectsParams: (state) => state.projectsParams,
        getProjectsPagination: (state) => state.projectsPagination,
        getProjectsTotalElements: (state) => state.projectsTotalElements,
        getProjectsTotalPages: (state) => state.projectsTotalPages,
    },
    mutations: {
        setProject(state, data) {
            state.project = data;
        },
        initProject(state) {
            state.project = { ...defaultProject };
        },
        setProjects(state, data) {
            state.projects = data;
        },
        setProjectsParams(state, data) {
            state.projectsParams = data;
        },
        initProjectsParams(state) {
            state.projectsParams = { ...defaultProjectParams };
        },
        setProjectsPagination(state, data) {
            state.projectsPagination = data;
        },
        initProjectsPagination(state) {
            state.projectsPagination = { ...defaultProjectPagination };
        },
        setProjectsTotalElements(state, data) {
            state.projectsTotalElements = data;
        },
        setProjectsTotalPages(state, data) {
            state.projectsTotalPages = data;
        },
    },
    actions: {
        async listProjects({ commit, getters }) {
            let params = getters.getProjectsParams;
            const pagination = getters.getProjectsPagination;
            params.size = pagination.size;
            params.page = pagination.page - 1;
            await axios.get(URL_PROJECTS).then((response) => {
                console.log(response);
                const data = response.data;
                console.log(data);
                commit("setProjects", data); // Use placeholder projects for now
                commit("setProjectsTotalPages", response.data.totalPages);
                commit("setProjectsTotalElements", response.data.totalElements);
            });
        },
        getProject({ commit }, id) {
            axios
                .get(`${URL_PROJECTS}/${id}`)
                .then((response) => {
                    console.log(response);
                    const project = response.data;
                    commit("setProject", project);
                })
                .catch((error) => {
                    console.log(error);
                });
        },
        createProject({ getters, commit }) {
            const project = getters.getProject;
            console.log(project);
            return axios.post(URL_PROJECTS, project);
        },
        async deleteProject({ }, id) {
            await axios.delete(`${URL_PROJECTS}/${id}`).then((response) => {
                console.log(response);
            });
        },
    },
};
