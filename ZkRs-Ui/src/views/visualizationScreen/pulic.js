export function processing(key, value){
  var arrData = []
  for (let i = 0; i < key.length; i++) {
    arrData.push({
      name:key[i],
      value:Number(value[i])
    })
  }
  return arrData
}


export function processingString(key, value){
  var arrData = []
  for (let i = 0; i < key.length; i++) {
    arrData.push({
      name:key[i],
      value:value[i]
    })
  }
  return arrData
}



export function projectStatusCharts(key, newList, oldList){
  var arrData = []
  for (let i = 0; i < key.length; i++) {
    arrData.push({
      name:key[i],
      value:Number(newList[i]) + Number(oldList[i]),
      newData:Number(newList[i]),
      oldData:Number(oldList[i])
    })
  }
  return arrData
}
