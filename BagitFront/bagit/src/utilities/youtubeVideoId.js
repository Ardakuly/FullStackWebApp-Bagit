
export function extractVideoId(url) {
    const pattern = /^.*(youtu.be\/|v\/|u\/\w\/|embed\/|watch\?v=|&v=)([^#&?]*).*/;
    const match = url.match(pattern);
    return match && match[2].length === 11 ? match[2] : null;
}