import hashlib

def md5_hash(text):
    return hashlib.md5(text.encode()).hexdigest()

def sha1_hash(text):
    return hashlib.sha1(text.encode()).hexdigest()

text = input("Enter the text to hash: ")

print("MD5 Hash:  ", md5_hash(text))
print("SHA-1 Hash:", sha1_hash(text))