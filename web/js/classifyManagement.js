var vm = new Vue({
    el: '#app',
    data() {
        return {
            nameIn: '',
            colorIn: '',
            addDialogVisible: false,
            editDialogVisible: false,
            classify: [
                {
                    name: '',
                    color: '',
                    diaryNum: '',
                }
            ],
            classifyTableData: [{
                classifyName: '旅游',
                classifyColor: '#ffff',
                classifyNum: 12
            }, {
                classifyName: '旅游',
                classifyColor: '#ffff',
                classifyNum: 12
            }, {
                classifyName: '旅游',
                classifyColor: '#ffff',
                classifyNum: 12
            }, {
                classifyName: '旅游',
                classifyColor: '#ffff',
                classifyNum: 12
            }, {
                classifyName: '旅游',
                classifyColor: '#ffff',
                classifyNum: 12
            }, {
                classifyName: '旅游',
                classifyColor: '#ffff',
                classifyNum: 12
            }, {
                classifyName: '旅游',
                classifyColor: '#ffff',
                classifyNum: 12
            }, {
                classifyName: '旅游',
                classifyColor: '#ffff',
                classifyNum: 12
            }, {
                classifyName: '旅游',
                classifyColor: '#ffff',
                classifyNum: 12
            }, {
                classifyName: '旅游',
                classifyColor: '#ffff',
                classifyNum: 12
            },
                {
                    classifyName: '旅游',
                    classifyColor: '#ffff',
                    classifyNum: 12
                }
            ],

        }
    },
    methods: {
        $refs: undefined,
        handleClose(index) {
            this.$refs[`popover-${index}`].doClose()
        },
        /*点击添加分类按钮 */
        addClassify: function () {
            this.addDialogVisible = true;
        },
        /*点击添加分类 确定按钮 把数据更新到后台*/
        addBtn: function () {
            this.addDialogVisible = false;

            axios({
                url: 'addClassify.do',
                method: 'post',
                data: {
                    'id': 0,//占位
                    'Name': '测试分类',
                    'Flag': false,
                    'Color': '#0x3f3f3f3f'
                }
                // long id, String name, boolean flag, String color
            }).then(function (resopnse) {
                if (resopnse.data == "error") {
                    this.$message("添加分类失败！");
                } else {
                    this.$message("成功添加新的分类");
                }
            }).catch(function (error) {
                this.$message("添加分类出错！" + error);
            })
        },
        /*点击编辑按钮*/
        editClick: function () {
            this.editDialogVisible = true;
        },
        /*点击编辑中确定按钮*/
        editSureClick: function () {
            this.editDialogVisible = false;
        }
    },
})
