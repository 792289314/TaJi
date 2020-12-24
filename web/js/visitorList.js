var vm = new Vue({
        el: '#app',
        data() {
            return {
                allDiaryList:[]
            }
        },
        created(){
            this.getAllDiary();
        },
        methods: {
            getAllDiary:function()
            {

            },
            showElement: function () {
                for (let i = 0; i < 7; i++) {
                    let div = document.createElement('div');
                    div.style.width='100%';
                    div.style.border = '1px solid black';
                    // div.innerHTML = '{{左边}}';
                    div.innerHTML = this.allDiaryList[i].diaryText;

                    div.id = 'showD' + i;
                    div.style.minHeight = "100px";
                    document.getElementById('showDiary').appendChild(div);

                }
            }

        },
    });