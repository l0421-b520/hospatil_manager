var vm= new Vue({
    el:'#app',
    data:{
        postList:[],
        page:{},
        pageNum:1,
        pageSize:5,
        search:{},
        meunlist:[]
    },
    methods:{
        findAll:function () {
            var _this = this
            axios.post('../po/getPostList.do?pageNum='+this.pageNum+'&pageSize='+this.pageSize,this.search).then(function (response) {
                console.log(response.data)
                _this.postList = response.data.list
                _this.page = response.data
                _this.pageNum = response.data.currentPage
                _this.pageSize=response.data.pageSize
            })
        },
        packing:function (pageNum) {
            this.pageNum = pageNum
            this.findAll();
        },
        fpQx:function (pid) {
            var _this = this
            axios.post('/po/getMeunListById.do?id='+pid).then(function (response) {
                _this.meunlist = response.data
                treeMsg(response.data,pid);
                document.getElementById("trees").style.display="block"
            })
        },
        savePostMeun:function (postid,ids) {
            var _this =this
            axios.post('/po/savePostMeun.do?postid='+postid,ids).then(function (response) {
                if (response.data=="yes"){
                    alert("修改成功！")
                    document.getElementById("trees").style.display="none"
                }else{
                    alert("修改失败！")
                }
            })
        }
    }
})
vm.findAll();