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
            classify: [
                {
                    name: '',
                    color: '',
                    diaryNum: '',
                }
            ],

            /*
                现在 classifyTableData 改成这种格式了 ⬇
               {"color":"#3f3f3f3f","flag":false,"id":1,"name":"未命名",cnt:9}
               {"color":"#4a4a4a4a","flag":false,"id":2,"name":"学习",cnt:10}
           */
            classifyTableData: [{
                classifyName: '旅游',
                classifyColor: '#89cd91',
                classifyNum: 12,
                classifyState:"公开",
            }, {
                classifyName: '旅游',
                classifyColor: '#cd4a57',
                classifyNum: 12,
                classifyState:"公开",
            }, {
                classifyName: '旅游',
                classifyColor: '#4134cd',
                classifyNum: 12,
                classifyState:"公开",
            }, {
                classifyName: '旅游',
                classifyColor: '#cd4a57',
                classifyNum: 12,
                classifyState:"公开",
            }, {
                classifyName: '旅游',
                classifyColor: '#5fcd86',
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
                    classifyColor: '#a6a8cd',
                    classifyNum: 12,
                    classifyState:"公开",
                }
            ],

        }
    },

    created() {
        //classifyTableData
        this.getAllClassifies();

    },

    methods: {
        exitMainBtn:function()
        {
            window.history.back()
        },
        // 界面刚载入时，获取用户所有的分类信息
        getAllClassifies: function () {
            const self = this;
            axios({
                url: 'getClassify.do', // 突然发现 这个功能在diaryMain.html界面里写过
                method: 'post'
            }).then(function (response) {
                if (response.data != "error") {
                    self.classifyTableData = response.data();
                }
            }).catch(function (error) {
                this.$message("获取用户分类信息发生错误 " + error);
            })
        },

        // 与关闭相关的代码(...我也没看懂 =。= 另一个队友写的)
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
