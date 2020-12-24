

var Main = {
    data() {
        return {
            visible: false,
        };
    }
};
var Ctor = Vue.extend(Main);
new Ctor().$mount('#app')
