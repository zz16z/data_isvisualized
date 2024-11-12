export function fontSize(res) {
  const clientWidth = window.innerWidth || document.documentElement.clientWidth || document.body.clientWidth;
  if(!clientWidth) return;
  let fontSize = clientWidth / 1920;
  return res*fontSize;
}


//数字格式化

export function numberFormat(val){
  val = Number(val)
  let num = 10000;
  var sizesValue = ''
  var sizes;
  if(val > 0){
    /**
     * 判断去那个单位
     * */

    if(val < 10000){
      sizesValue = ''
    }else if(val > 1000 && val < 9999){
      sizesValue = '千'
    }else if(val > 10000 && val < 99999999){
      sizesValue = '万'
    }else if(val > 100000000){
      sizesValue = '亿'
    }


    /**
     * 大于一万则
     * */
    let i = Math.floor(Math.log(val) / Math.log(num))
    /**
     * toFixed(2)
     * */
    sizes = ((val / Math.pow(num, i))).toFixed(2)
    sizes = sizes + sizesValue
  }else{
    sizes = 0
  }
  return sizes
}


