import axios from 'axios';
const URL_FILES = '/files';


export default {
    state: {
        file: {},
        fileInfo: {},
        files: [],
        filesInfos: []
    },
    getters: {
        getFile: state => state.file,
        getFileInfo: state => state.fileInfo,
        getFiles: state => state.files,
        getFilesInfos: state => state.filesInfos
    },
    mutations: {
        setFile(state, data) {
            state.file = data;
        },
        setFileInfo(state, data) {
            state.fileInfo = data;
        },
        initFileInfo(state) {
            state.fileInfo = null
        },
        setFiles(state, data) {
            state.files = data;
        },
        setFilesInfos(state, data) {
            state.filesInfos = data;
        }
    },
    actions: {
        async uploadFiles(state) {
            var formData = new FormData();
            var files = state.getters.getFiles;
            if (files.length == 0) {
                return;
            }
            for (let i = 0; i < files.length; i++) {
                formData.append('files', files[i]);
            }

            await axios.post(URL_FILES + '/upload', formData)
                .then(response => {
                    var filesInfos = state.getters.getFilesInfos;
                    state.commit("setFilesInfos", filesInfos.concat(response.data))
                })
                .catch(error => {
                    state.commit("setFilesInfos", [])
                    console.error(error);
                });
        },
        async uploadFile(state) {
            var file = state.getters.getFile;
            var formData = new FormData();
            formData.append('file', file);
            await axios.post(URL_FILES, formData)
                .then(response => {
                    state.commit("setFileInfo", response.data)
                })
                .catch(error => {
                    state.commit("initFileInfo")
                    console.error(error);
                });
        },
        async uploadFileConvert(state) {
            var file = state.getters.getFile;
            var formData = new FormData();
            formData.append('file', file);
            await axios.post(`${URL_FILES}/upload-convert`, formData)
                .then(response => {
                    state.commit("setFileInfo", response.data)
                })
                .catch(error => {
                    state.commit("initFileInfo")
                    console.error(error);
                });
        },
        async uploadZip(state) {
            var file = state.getters.getFile;
            var formData = new FormData();
            formData.append('file', file);
            await axios.post(URL_FILES + "/uploadZip", formData)
                .then(response => {
                    var filesInfos = state.getters.getFilesInfos;
                    state.commit("setFilesInfos", filesInfos.concat(response.data))
                })
                .catch(error => {
                    state.commit("setFilesInfos", [])
                    console.error(error);
                });
        }

    }
}
