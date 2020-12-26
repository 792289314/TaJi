var vm = new Vue({
        el: '#app',
        data() {
            return {
                nameIn:'',
                colorIn:'',
                addDialogVisible:false,
                editDialogVisible:false,
                classify:[
                    {
                        name:'',
                        color:'',
                        diaryNum:'',
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
            addClassify:function () {
                this.addDialogVisible=true;
            },
            /*点击添加分类 确定按钮 把数据更新到后台*/
            addBtn:function () {
                this.addDialogVisible=false;
            },
            /*点击编辑按钮*/
            editClick:function () {
                this.editDialogVisible=true;
            },
            /*点击编辑中确定按钮*/
            editSureClick:function () {
                this.editDialogVisible=false;
            }
        },
    })
