export const printTimestamp = (timeStamp: number): string  => {
  const currentDate = new Date();
  const inputDate = new Date(timeStamp);
  if (
    inputDate.getDate() === currentDate.getDate() &&
    inputDate.getMonth() === currentDate.getMonth() &&
    inputDate.getFullYear() === currentDate.getFullYear()
  ) {
    const hours = inputDate.getHours().toString().padStart(2, '0');
    const minutes = inputDate.getMinutes().toString().padStart(2, '0');
    return `${hours}h${minutes}`;
  } else {
    const day = inputDate.getDate().toString().padStart(2, '0');
    const month = (inputDate.getMonth() + 1).toString().padStart(2, '0');
    const year = inputDate.getFullYear();
    return `${day}/${month}/${year}`;
  }
}
