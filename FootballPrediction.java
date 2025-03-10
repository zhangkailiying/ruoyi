package com.ruoyi.project.system.domain.entity;

import java.util.HashMap;
import java.util.Map;

public class FootballPrediction {

    // 假设有两个队伍A和B，赔率格式为：胜-平-负
    private static final Map<String, double[]> odds = new HashMap<>();

    static {
        odds.put("Source1", new double[]{1.5, 3.0, 4.5}); // 示例赔率，A队
        odds.put("Source2", new double[]{2.0, 2.5, 3.5}); // 示例赔率，A队
        odds.put("Source3", new double[]{1.8, 3.2, 4.0}); // 示例赔率，B队
        odds.put("Source4", new double[]{1.6, 2.8, 4.2}); // 示例赔率，B队
    }

    // 简化的赔率到概率的转换函数（仅为示例，实际转换更复杂）
    private static double[] oddsToProbabilities(double[] oddsArray) {
        double sum = oddsArray[0] + oddsArray[1] + oddsArray[2];
        return new double[]{oddsArray[0] / sum, oddsArray[1] / sum, oddsArray[2] / sum};
    }

    // 计算权重（此处为示例，采用简单的平均权重分配）
    // 实际应用中，权重可能基于赔率的可信度、预测源的信誉等因素确定
    private static double[] calculateWeights(double[][] probabilities) {
        double totalWeight = 0.0;
        double[] weights = new double[probabilities.length];

        // 假设所有预测源权重相等（简化处理）
        for (int i = 0; i < probabilities.length; i++) {
            weights[i] = 1.0 / probabilities.length;
            totalWeight += weights[i];
        }

        // 验证总权重是否为1（理论上应为1，但此处为保险起见进行验证）
        if (Math.abs(totalWeight - 1.0) > 1e-6) {
            System.out.println("Warning: Total weight is not exactly 1.0");
        }

        return weights;
    }

    // 组合预测
    private static double[] combinePredictions(double[][] probabilities, double[] weights) {
        double[] combined = new double[3]; // 胜-平-负

        for (int i = 0; i < probabilities.length; i++) {
            for (int j = 0; j < 3; j++) {
                combined[j] += probabilities[i][j] * weights[i];
            }
        }

        // 归一化组合预测结果，使其和为1
        double sum = combined[0] + combined[1] + combined[2];
        combined[0] /= sum;
        combined[1] /= sum;
        combined[2] /= sum;

        return combined;
    }

    public static void main(String[] args) {
        // 获取A队和B队的赔率，并转换为概率
        double[][] probabilitiesA = new double[odds.size() / 2][3];
        double[][] probabilitiesB = new double[odds.size() / 2][3];
        int index = 0;

        for (Map.Entry<String, double[]> entry : odds.entrySet()) {
            if (index < odds.size() / 2) {
                probabilitiesA[index] = oddsToProbabilities(entry.getValue());
            } else {
                probabilitiesB[index - odds.size() / 2] = oddsToProbabilities(entry.getValue());
            }
            index++;
        }

        // 计算A队和B队的预测权重（此处为简化处理，实际应用中需根据具体情况确定）
        double[] weightsA = calculateWeights(probabilitiesA);
        double[] weightsB = calculateWeights(probabilitiesB); // 注意：B队的权重计算应基于其自己的赔率数据

        // 进行组合预测
        double[] combinedPredictionA = combinePredictions(probabilitiesA, weightsA);
        double[] combinedPredictionB = combinePredictions(probabilitiesB, weightsB);

        // 输出预测结果
        System.out.println("A队获胜概率: " + combinedPredictionA[0]);
        System.out.println("A队平局概率: " + combinedPredictionA[1]);
        System.out.println("A队失利概率: " + combinedPredictionA[2]);

        System.out.println("B队获胜概率: " + combinedPredictionB[0]);
        System.out.println("B队平局概率: " + combinedPredictionB[1]);
        System.out.println("B队失利概率: " + combinedPredictionB[2]);
    }
}