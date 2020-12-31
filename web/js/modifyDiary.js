var vm = new Vue({//无用的页面
    el: '#app',
    data() {
        return {
            name: '111',
            text1: 'hjkhjkhjkkj',
            deleteDialogVisible: false,
        }
    },
    create: {},
    methods:
        {
            /* 删除日记*/
            deleteBtn: function () {
                this.deleteDialogVisible = true;
            },
            /* 确定删除日记*/
            sureDeleteClick: function () {
                this.deleteDialogVisible = false
            },
            /* 日记div*/
            editDiaryBox: function () {

            },
            /* 保存日记*/
            saveClick: function () {

            },
        },
});
