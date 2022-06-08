export function removeTAG(str){
  return str.replace(/<[^>]+>/g,"");
}