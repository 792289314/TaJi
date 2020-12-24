

var vm = new Vue({
        el: '#app',
        data() {
            return {

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
            addClassify:function () {

            }


        },
    })
