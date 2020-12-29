var vm = new Vue({
    el: '#app',
    data() {
        return {
            //添加分类需要的分类名 颜色 状态
            nameIn: '',
            colorIn: '',
            stateIn:'',
            stateOptions: [{
                value: '选项1',
                label: '私密'
            }, {
                value: '选项2',
                label: '公开'
            }],
            stateValue:'',
            //--------------------------------------分割线
            addDialogVisible: false,
            editDialogVisible: false,
            //显示分类的table需要的数据
            classify: [
                {
                    name: '',
                    color: '',
                    diaryNum: '',

                },
            ],

            //---------------------------------------分割线
            classifyTableData: [{
                classifyName: '旅游',
                classifyColor: '#cd4a57',
                classifyNum: 12,
                classifyState:"公开",
            }, {
                classifyName: '旅游',
                classifyColor: '#cd4a57',
                classifyNum: 12,
                classifyState:"公开",
            }, {
                classifyName: '旅游',
                classifyColor: '#cd4a57',
                classifyNum: 12,
                classifyState:"公开",
            }, {
                classifyName: '旅游',
                classifyColor: '#cd4a57',
                classifyNum: 12,
                classifyState:"公开",
            }, {
                classifyName: '旅游',
                classifyColor: '#cd4a57',
                classifyNum: 12,
                classifyState:"公开",
            }, {
                classifyName: '旅游',
                classifyColor: '#cd4a57',
                classifyNum: 12,
                classifyState:"公开",
            },
                {
                    classifyName: '旅游',
                    classifyColor: '#cd4a57',
                    classifyNum: 12,
                    classifyState:"公开",
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
});
