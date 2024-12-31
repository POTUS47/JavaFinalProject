import clip
import torch
from PIL import Image
from torch.nn.functional import normalize


# 提取文本特征(文本可多个)
def extract_description_feature(description, model, device):
    # 如果输入是单个文本，转化为列表
    if isinstance(description, str):
        description = [description]
    description_tokenized = clip.tokenize(description).to(device)
    with torch.no_grad():
        description_features = model.encode_text(description_tokenized)
        description_features = normalize(description_features, p=2, dim=-1)  # L2 归一化
    return description_features


# 提取图像特征
def extract_image_feature(image_path, model, preprocess, device):
    image = preprocess(Image.open(image_path)).unsqueeze(0).to(device)
    with torch.no_grad():
        image_features = model.encode_image(image)
        image_features = normalize(image_features, p=2, dim=-1)  # L2 归一化
    return image_features


# 计算余弦相似度(两个所输入向量必须已归一化)
def calculate_similarity(feature_vector1, feature_vector2):
    # 确保输入是 Tensor
    if not isinstance(feature_vector1, torch.Tensor) or not isinstance(feature_vector2, torch.Tensor):
        raise ValueError("Input vectors must be torch.Tensor")
    similarity = (feature_vector1 @ feature_vector2.T).item()
    return similarity





