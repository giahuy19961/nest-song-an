export const getOptions = (data,fieldValue='id',fieldLabel='name') =>{
    return data.map((item)=>{
        return ({
            label:item[fieldLabel],
            value:item[fieldValue]
        })
    })
}