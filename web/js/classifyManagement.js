var vm = new Vue({
    el: '#app',
    component:['el'],
    data() {
        return {
            //添加分类需要的分类名 颜色 状态
            nameIn: '',
            colorIn: '',
            stateIn: '',
            stateOptions: [
                {
                    value: false,
                    label: '公开'
                },
                {
                    value: true,
                    label: '私密'
                }],
            stateValue: false,
            //--------------------------------------分割线
            addDialogVisible: false,
            editDialogVisible: false,
            classify:
                {
                    id: '',
                    name: '',
                    color: '',
                    diaryNum: '',
                }
            ,

            /*
                后端传来的给classifyTableData的数据格式 ⬇
               {"color":"#3f3f3f3f","flag":false,"id":1,"name":"未命名",cnt:9}
           */
            classifyTableData: [{
                classifyName: '',
                classifyColor: '#89cd91',
                classifyNum: 12,
                classifyState: "公开",
            },
            ],

        }
    },

    created() {
        //classifyTableData
        this.getAllClassifies();

    },

    methods: {
        // 界面刚载入时，获取用户所有的分类信息
        getAllClassifies: function () {
            const self = this;
            axios({
                url: 'getAllClassifiesExceptUnClassified.do',
                method: 'post'
            }).then(function (response) {
                if (response.data != "error") {
                    self.classifyTableData = response.data;
                } else {
                    self.$message.error("页面刷新失败");
                }
            }).catch(function (error) {
                self.$message("获取用户分类信息发生错误 " + error);
            })
        },

        /*点击添加分类按钮 */
        addClassify: function () {
            this.stateValue = false;
            this.addDialogVisible = true;
        },

        judgeClassifyName: function () {
            const name = this.nameIn;
            if (name == '全部' || name == '未分类') {
                this.$message('非法取名！换一个吧！');
                return false;
            }
            for (var i = 0; i < this.classifyTableData.length; i++) {
                if (this.classifyTableData[i].name == name) {
                    this.$message('该分类已经存在！换一个吧！');
                    return false;
                }
            }
            return true;
        },

        /*点击添加分类 确定按钮 把数据更新到后台*/
        addBtn: function () {
            this.addDialogVisible = false;

            const self = this;

            if (!this.judgeClassifyName()) {

            } else {
                axios({
                    url: 'addClassify.do',
                    method: 'post',
                    data: {
                        id: 0,//占位 和后台直接 json转实例 有关
                        name: self.nameIn,
                        flag: self.stateValue,
                        color: self.colorIn,
                        cnt: 0
                    }
                    // long id, String name, boolean flag, String color
                }).then(function (response) {
                    if (response.data == "error") {
                        self.$message.error("添加分类失败！");
                    } else {
                        self.$message({
                            message:"成功添加新的分类",
                            type: 'success'
                        });
                        self.getAllClassifies();
                    }
                }).catch(function (error) {
                    self.$message.error("添加分类出错！" + error);
                })
            }

        },


        /*点击编辑按钮*/
        editClick: function (index) {
            this.classify.id = this.classifyTableData[index].id;
            this.classify.name = this.classifyTableData[index].name;
            this.classify.color = this.classifyTableData[index].color;
            this.classify.diaryNum = this.classifyTableData[index].cnt;
            this.stateValue = this.classifyTableData[index].flag;

            //东西都准备好后 显示界面
            this.editDialogVisible = true;
        },

        /*点击编辑中确定按钮*/
        editSureClick: function () {
            this.editDialogVisible = false;
            const self = this;
            axios({
                url: 'modifyClassify.do',
                method: 'post',
                data: {
                    id: self.classify.id,
                    name: self.classify.name,
                    flag: self.stateValue,
                    color: self.classify.color,
                    cnt: 0//占位 后台生成实例用
                }
            }).then(function (response) {
                if (response.data == "error") {
                    self.$message.error("修改分类失败！");
                } else {
                    self.$message({
                        message:"成功修改分类",
                        type: 'success'
                    });
                    self.getAllClassifies();
                }
            }).catch(function (error) {
                self.$message.error("修改分类出错！" + error);
            })

        },

        // 删除中的确定按钮
        deleteClick: function (index) {
            const self = this;
            axios({
                url: 'deleteClassify.do',
                method: 'post',
                data: {classifyId: self.classifyTableData[index].id}
            }).then(function (response) {
                if (response.data == "error") {
                    self.$message.error("删除失败！");
                } else {
                    self.$message({
                        message:"成功删除！",
                        type: 'success'
                    });
                    self.getAllClassifies();
                }
            }).catch(function (error) {
                self.$message.error("获取用户分类信息发生错误 " + error);
            })

        },
        returnClick:function () {
            window.location.href = document.referrer;//跳转上一个页面并刷新
        },
    },
});
